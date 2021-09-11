package com.legyver.fenxlib.widgets.filetree.scan;

import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;

/**
 * Handle filesystem events triggered when a watched folder or file has a directory or file created under it, or is otherwise renamed/updated/deleted.
 */
public interface FileSystemEventHandler {
    /**
     * Handle the filesystem event
     * @param fileTreeRegistry the registry to register the file with in the event of a CREATE event
     * @param fileSystemEvent the event information
     */
    void handle(FileTreeRegistry fileTreeRegistry, FileSystemEvent fileSystemEvent);
}
