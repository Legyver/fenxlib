package com.legyver.fenxlib.widgets.filetree.scan;

import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;

import java.nio.file.WatchEvent;

/**
 * Container for transmitting filesystem events when a file has been created/updated/deleted
 */
public class FileSystemEvent {
    /**
     * The parent directory.  When a directory/file is created, it is actually picked up as a modification of the watched directory
     */
    private final IFileReference parentFileReference;
    /**
     * The directory/file itself
     */
    private final IFileReference fileReference;
    /**
     * The triggered event
     */
    private final WatchEvent<?> event;

    /**
     * Create a FileSystemEvent with the given parent and file references along with the original filesystem watch triggered
     * @param parentFileReference the parent file
     * @param fileReference       the changed/modified/deleted file/directory
     * @param event               the watch event triggered
     */
    public FileSystemEvent(IFileReference parentFileReference, IFileReference fileReference, WatchEvent<?> event) {
        this.parentFileReference = parentFileReference;
        this.fileReference = fileReference;
        this.event = event;
    }

    /**
     * Get the parent file reference.  This will usually be the parent directory.
     * @return the parent file reference.
     */
    public IFileReference getParentFileReference() {
        return parentFileReference;
    }

    /**
     * Get the file reference the event pertains to
     * @return the file reference
     */
    public IFileReference getFileReference() {
        return fileReference;
    }

    /**
     * Get the {@link WatchEvent} fired by the filesystem watch
     * @return the watch event
     */
    public WatchEvent<?> getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "FileSystemEvent{" +
                "parentFileReference=" + parentFileReference +
                ", fileReference=" + fileReference +
                ", event=" + event +
                '}';
    }
}
