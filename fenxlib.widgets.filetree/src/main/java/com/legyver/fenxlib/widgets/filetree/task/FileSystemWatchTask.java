package com.legyver.fenxlib.widgets.filetree.task;

import com.legyver.fenxlib.extensions.tuktukfx.task.AbstractCorrelatingObservableTask;
import com.legyver.tuktukfx.processor.TaskProcessor;

/**
 * FileSystem watch task.  The actual work is done in the {@link FileSystemWatchTaskProcessor}
 */
public class FileSystemWatchTask extends AbstractCorrelatingObservableTask<Void, FileSystemWatchTaskContext> {

    /**
     * Pass in the main task context here.  This is exists mainly as a way to pass arguments to your task processor.
     * However, it also serves to communicate the domain/range size to any observers for status reporting.
     *
     * @param timingData : context for the task
     */
    public FileSystemWatchTask(FileSystemWatchTaskContext timingData) {
        super(timingData);
    }

    @Override
    public TaskProcessor getTaskProcessor() {
        return new FileSystemWatchTaskProcessor(context);
    }

}
