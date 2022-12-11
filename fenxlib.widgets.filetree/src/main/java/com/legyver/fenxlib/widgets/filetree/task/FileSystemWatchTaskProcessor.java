package com.legyver.fenxlib.widgets.filetree.task;

import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import com.legyver.fenxlib.widgets.filetree.scan.FileSystemEvent;
import com.legyver.fenxlib.widgets.filetree.scan.IFileWatchHandler;
import com.legyver.tuktukfx.adapter.AbortableTaskStatusAdapter;
import com.legyver.tuktukfx.processor.TaskProcessor;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.legyver.fenxlib.widgets.filetree.scan.FileWatcherEventConstants.*;
import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Monitor all watched directories.
 * We create one Watcher service for each directory and iteratively poll each one in turn.
 *
 * This class handles the following for you:
 * - On sub-directory deletions, removes any obsolete WatchServices
 * - On sub-directory creations, adds a new WatchService
 * - On all events, dispatches the event to the provided {@link IFileWatchHandler}
 * - Monitors {@link #knownFiles} for added files which (if directories) will then be watched
 */
public class FileSystemWatchTaskProcessor implements TaskProcessor<AbortableTaskStatusAdapter> {
    private static final Logger logger = LogManager.getLogger(FileSystemWatchTaskProcessor.class);

    /**
     * List of files to track
     */
    private final ObservableList<? extends IFileReference> knownFiles;
    /**
     * Handler to be notified of any changes in files
     */
    private final IFileWatchHandler fileWatchHandler;

    //internal maps
    private final Map<String, IFileReference> fileReferenceMap = new HashMap<>();
    private final Map<String, FileSystemWatchTaskProcessor.WatchReference> watchReferenceMap = new HashMap<>();
    //internal semaphores
    //block when there are no filesystem items to monitor
    private final Semaphore trackedValuesCountingSemaphore = new Semaphore(0);
    //block when updating the internal maps
    private final Semaphore iterationMutex = new Semaphore(1);

    private TreeView treeView;

    /**
     * Construct a task processor to process watches on the filesystem
     * @param context the context for the filesystem watch
     */
    public FileSystemWatchTaskProcessor(FileSystemWatchTaskContext context) {
        this.knownFiles = context.getKnownFiles();
        this.fileWatchHandler = context.getFileWatchHandler();
    }

    /**
     * Setup watches on all known files and poll them for changes.
     * If a file is created/deleted/modified, notify the handler of the event.
     * @param taskStatusAdapter adapter for echoing status to the UI.  Not used in this context
     */
    @Override
    public void process(AbortableTaskStatusAdapter taskStatusAdapter) {
        addWatches(knownFiles);
        updateMap(knownFiles);
        knownFiles.addListener((ListChangeListener<IFileReference>) c -> {
            c.next();
            if (c.wasAdded()) {
                try {
                    logger.trace("Acquiring mutex to add items.  Permits:[{}]",  iterationMutex.availablePermits());
                    iterationMutex.acquire();
                    List<? extends IFileReference> added = c.getAddedSubList();
                    addWatches(added);
                    updateMap(added);
                    logger.debug("Adding {} items.  Watch size now {}", added.size(), watchReferenceMap.size());
                    trackedValuesCountingSemaphore.release(added.size());
                } catch (InterruptedException e) {
                    logger.error("Error acquiring mutex before add monitored item", e);
                } catch (RuntimeException rex) {
                    logger.error("Exception adding file to observed items", rex);
                } finally {
                    iterationMutex.release();
                    logger.trace("Released mutex after adding items.  Permits:[{}]",  iterationMutex.availablePermits());
                }
            } else if (c.wasRemoved()) {
                try {
                    trackedValuesCountingSemaphore.acquire(c.getRemovedSize());
                    log(c.getRemoved(), "{} deleted from observed file reference list");
                    logger.debug("Removing {} items.  Watch size now {}", c.getRemovedSize(), watchReferenceMap.size());
                } catch (InterruptedException ex) {
                    logger.error("Error acquiring necessary permits after item removed");
                } catch (RuntimeException rex) {
                    logger.error("Exception removing files from monitored items", rex);
                }
            }
        });
        runUntilAbort(taskStatusAdapter);
        logger.debug("File system watch terminating");
    }

    private void runUntilAbort(AbortableTaskStatusAdapter taskStatusAdapter) {
        int lastSize = 1;
        while (!taskStatusAdapter.isAborted()) {
            try {
                //block until an item has been added to the list
                //we have a timeout on this because we want also be able to respond to abort events
                long timeout = lastSize == 0 ? 2000 : 1000;
                boolean acquired = trackedValuesCountingSemaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
                if (!acquired) {
                    logger.trace("counting semaphore not acquired");
                } else {
                    int size = watchReferenceMap.size();
                    if (size != lastSize) {
                        if (logger.isTraceEnabled()) {
                            logger.trace("Watches: [{}]", watchReferenceMap.keySet().stream().collect(Collectors.joining(",")));
                        }
                        lastSize = size;
                    }

                    logger.trace("Acquiring mutex to check watches.  Permits:[{}]",  iterationMutex.availablePermits());
                    iterationMutex.acquire();
                    logger.trace("Acquired mutex to check watches.  Permits:[{}]",  iterationMutex.availablePermits());
                    List<String> removeKeys = checkWatches(taskStatusAdapter);
                    if (removeKeys != null) {
                        if (logger.isTraceEnabled()) {
                            logger.trace("remove keys [{}]", removeKeys.stream().collect(Collectors.joining(",")));
                        }
                        for (String uniqueIdentifier : removeKeys) {
                            logger.debug("Removing watch reference: {}", uniqueIdentifier);
                            watchReferenceMap.remove(uniqueIdentifier);
                            fileReferenceMap.remove(uniqueIdentifier);
                        }
                    }

                    logger.trace("Releasing mutex after checking watches.  Permits:[{}]",  iterationMutex.availablePermits());
                    iterationMutex.release();
                    logger.trace("Released mutex after checking watches.  Permits:[{}]",  iterationMutex.availablePermits());

                    logger.trace("Releasing counting semaphore after checking watches.  Permits:[{}]",  iterationMutex.availablePermits());
                    trackedValuesCountingSemaphore.release();
                    logger.trace("Released counting semaphore after checking watches.  Permits:[{}]",  iterationMutex.availablePermits());
                }//end-if-acquired
            } catch (InterruptedException exception) {
                logger.error("Interrupted exception acquiring semaphore", exception);
            } catch (RuntimeException exception) {
                logger.error("Exception encountered in processing", exception);
            }
        }
    }

    private List<String> checkWatches(AbortableTaskStatusAdapter taskStatusAdapter) {
        List<String> removeKeys = null;
        for (String uniqueIdentifier: watchReferenceMap.keySet()) {
            //Re-check isAborted() below to speed up abort handling
            if (taskStatusAdapter.isAborted()) {
                break;
            }

            WatchReference watchReference = watchReferenceMap.get(uniqueIdentifier);
            if (!watchReference.isValid) {
                if (removeKeys == null) {
                    removeKeys = new ArrayList<>();
                }
                removeKeys.add(uniqueIdentifier);
                logger.debug("Watch reference not valid and is being flagged for removal: ", watchReference.fileReference.getUniqueIdentifier());
            } else {
                pollWatch(watchReference);
            }
        }
        return removeKeys;
    }

    private void pollWatch(WatchReference watchReference) {
        logger.trace("Polling watch for {}", watchReference.fileReference.getUniqueIdentifier());
        FileSystemEvent fileSystemEvent = null;
        WatchKey key = watchReference.watchService.poll();
        if (key == null) {
            logger.trace("No changes to watch {}", watchReference.fileReference.getUniqueIdentifier());
        } else {
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // The filename is the context of the event.
                Path path = (Path) event.context();
                File directory = watchReference.fileReference.getFile();
                Path child = directory.toPath().resolve(path);
                String relativeName = logEvent(kind, child);

                File childFile = child.toFile();
                FileReference childFileReference = new FileReference(childFile);
                switch (kind.name()) {
                    case ENTRY_DELETE_NAME:
                        closeObsoleteWatchersRecursive(childFile);
                        break;
                    case ENTRY_CREATE_NAME:
                        if (childFile.isDirectory()) {
//                            addWatch(childFileReference);
                        }
                        break;
                    case OVERFLOW_NAME:
                        logger.error("OVERFLOW on watcher {} when handling {}", directory.getAbsolutePath(), relativeName);
                        break;
                }
                EventDispatch eventDispatch = new EventDispatch(fileWatchHandler);
                eventDispatch.fileSystemEvent = new FileSystemEvent(watchReference.fileReference, childFileReference, event);
                Platform.runLater(eventDispatch);
            }

            // Reset the key -- this step is critical if you want to
            // receive further watch events.  If the key is no longer valid, the directory is inaccessible
            boolean valid = key.reset();
            logger.trace("Key [{}] reset. Key is valid: {}", watchReference.fileReference.getUniqueIdentifier(), valid);

        }
    }

    private void closeObsoleteWatchersRecursive(File childFile) {
        FileReference childFileReference = new FileReference(childFile);
        if (childFile.isDirectory()) {
            //mark any watchers for the child directory and children for removal
            //and close their respective watch services
            WatchReference childWatchReference = watchReferenceMap.get(childFileReference.getUniqueIdentifier());
            if (childWatchReference != null) {
                childWatchReference.isValid = false;
                try {
                    childWatchReference.watchService.close();
                } catch (IOException e) {
                    logger.error("Unable to close WatchService for " + childFile.getAbsolutePath(), e);
                } catch (RuntimeException rex) {
                    logger.error("Unable to close watch service for " + childFile.getAbsolutePath(), rex);
                }
            }
            for (File childChild : childFile.listFiles()) {
                if (childChild.isDirectory()) {
                    closeObsoleteWatchersRecursive(childChild);
                }
            }
        }
    }

    private String logEvent(WatchEvent.Kind<?> kind, Path child) {
        StringJoiner stringJoiner = new StringJoiner(File.separator);
        for (int i = 0; i < child.getNameCount(); i++) {
            stringJoiner.add(child.getName(i).toString());
        }
        String result = stringJoiner.toString();
        logger.trace(result + " " + kind.name());
        return result;
    }

    private void updateMap(List<? extends IFileReference> added) {
        for (IFileReference fileReference : added) {
            fileReferenceMap.put(fileReference.getUniqueIdentifier(), fileReference);
        }
    }

    //in methods below, the assumption is that children will be managed independently
    private void addWatches(List<? extends IFileReference> added) {
        for (IFileReference fileReference : added) {
            addWatch(fileReference);
        }
    }

    private void addWatch(IFileReference fileReference) {
        File file = fileReference.getFile();
        Path filePath = Path.of(file.getPath());
        logger.debug("{} added to observed file reference list", file);
        if (file.isDirectory()) {
            WatchReference existing = watchReferenceMap.get(fileReference.getUniqueIdentifier());
            if (existing == null) {
                try {
                    WatchService watcher = FileSystems.getDefault().newWatchService();
                    filePath.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
                    watchReferenceMap.put(fileReference.getUniqueIdentifier(), new WatchReference(watcher, fileReference));
                    logger.debug("Added WatchService for {}", file);
                } catch (RuntimeException|IOException e) {
                    logger.error("Error registering watch on " + file, e);
                }
            } else {
                logger.trace("WatchService already exists for {}", file);
            }
        }
    }

    private void log(List<? extends IFileReference> references, String message) {
        for (IFileReference fileReference : references) {
            logger.trace(message, fileReference.getUniqueIdentifier());
        }
    }

    private class WatchReference {
        private final WatchService watchService;
        private final IFileReference fileReference;
        private boolean isValid = true;//watches are valid when added

        private WatchReference(WatchService watchService, IFileReference fileReference) {
            this.watchService = watchService;
            this.fileReference = fileReference;
        }
    }

    private static class EventDispatch implements Runnable {
        private final IFileWatchHandler fileWatchHandler;
        private FileSystemEvent fileSystemEvent;

        private EventDispatch(IFileWatchHandler fileWatchHandler) {
            this.fileWatchHandler = fileWatchHandler;
        }

        @Override
        public void run() {
            if (fileSystemEvent != null) {
                logger.trace("sending event [{}]", fileSystemEvent);
                fileWatchHandler.handle(fileSystemEvent);
            }
        }
    }

}
