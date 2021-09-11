package com.legyver.fenxlib.widgets.filetree.scan;


/**
 * Convenience constants for case statements corresponding to {@link java.nio.file.StandardWatchEventKinds}
 */
public interface FileWatcherEventConstants {
    /**
     * Directory entry created event.
     * Text value of ``{@link java.nio.file.StandardWatchEventKinds#ENTRY_CREATE}
     */
    String ENTRY_CREATE_NAME = "ENTRY_CREATE";
    /**
     * Directory entry deleted event.
     * Text value of {@link java.nio.file.StandardWatchEventKinds#ENTRY_DELETE}
     */
    String ENTRY_DELETE_NAME = "ENTRY_DELETE";
    /**
     * Directory entry modified event.
     * Text value of {@link java.nio.file.StandardWatchEventKinds#ENTRY_MODIFY}
     */
    String ENTRY_MODIFY_NAME = "ENTRY_MODIFY";
    /**
     * A special event to indicate that events may have been lost or discarded.
     * Text value of {@link java.nio.file.StandardWatchEventKinds#OVERFLOW}
     */
    String OVERFLOW_NAME = "OVERFLOW";
}
