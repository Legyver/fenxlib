package com.legyver.fenxlib.widgets.filetree.scan;

import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.WatchEvent;

/**
 * Handle OVERFLOW filesystem events
 */
public class FileSystemOverflowEventHandler implements FileSystemEventHandler {
    private static final Logger logger = LogManager.getLogger(FileSystemOverflowEventHandler.class);

    /**
     * Handle OVERFLOW event
     * @param fileTreeRegistry the registry to register the file with in the event of a CREATE event
     * @param fileSystemEvent the event information
     */
    @Override
    public void handle(FileTreeRegistry fileTreeRegistry, FileSystemEvent fileSystemEvent) {
        WatchEvent watchEvent = fileSystemEvent.getEvent();
        Object context = watchEvent.context();
        if (context == null) {
            logger.error("Overflow event detected processing file [{}].  No context information available.", fileSystemEvent.getFileReference().getUniqueIdentifier());
        } else {
            logger.error("Overflow event detected processing file [{}].  Context: {}", fileSystemEvent.getFileReference().getUniqueIdentifier(), context);
        }
    }
}
