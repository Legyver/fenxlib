package com.legyver.fenxlib.widgets.filetree.task;

import com.legyver.fenxlib.extensions.tuktukfx.task.CorrelatingTaskContext;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import com.legyver.fenxlib.widgets.filetree.scan.IFileWatchHandler;
import javafx.collections.ObservableList;

/**
 * Context for the {@link FileSystemWatchTask}.
 */
public class FileSystemWatchTaskContext extends CorrelatingTaskContext {

    /**
     * All known files to monitor on the filesystem
     */
    private final ObservableList<? extends IFileReference> knownFiles;
    /**
     * Handler to be notified of changes in the watched files on the filesystem
     */
    private final IFileWatchHandler fileWatchHandler;

    /**
     * Instantiate the task context with timing data
     * Sets the correlationId as 'File system watch'
     * @param knownFiles known files to monitor
     * @param fileWatchHandler handler to be notified of changes to the files
     */
    public FileSystemWatchTaskContext(ObservableList<? extends IFileReference> knownFiles, IFileWatchHandler fileWatchHandler) {
        super(1.0, "File system watch");//size doesn't actually matter since this task will run until the application shuts down
        this.knownFiles = knownFiles;
        this.fileWatchHandler = fileWatchHandler;
    }

    /**
     * Get the list of known files
     * @return list of known files
     */
    public ObservableList<? extends IFileReference> getKnownFiles() {
        return knownFiles;
    }

    /**
     * Get the file watch handler
     * @return the file watch handler
     */
    public IFileWatchHandler getFileWatchHandler() {
        return fileWatchHandler;
    }
}
