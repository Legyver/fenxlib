package com.legyver.fenxlib.widgets.filetree.scan;

/**
 * Handles FileSystemEvents triggered when a file is added or discovered
 */
public interface IFileWatchHandler {
    /**
     * Called when a filesystem watch detects a change in the filesystem.
     * @param fileSystemEvent the event that describes the change
     */
    void handle(FileSystemEvent fileSystemEvent);

    /**
     *
     * {@link com.legyver.fenxlib.widgets.filetree.BaseFileExplorer} calls this when a file/folder is added to the {@link com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry}
     *
     * @param eventKindName usually one of {@link FileWatcherEventConstants}
     * @param fileSystemEvent  the event that describes the change
     */
    void handle(String eventKindName, FileSystemEvent fileSystemEvent);
}
