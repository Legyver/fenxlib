package com.legyver.fenxlib.widgets.filetree.task;

import com.legyver.fenxlib.extensions.tuktukfx.task.adapter.JavaFxAdapter;

import static com.legyver.fenxlib.extensions.tuktukfx.bindings.TaskAbortBinding.taskAbortObservesShutdown;

/**
 * Factory to create a FileSystemWatch task.
 * As the task monitors the isAborted flag, the legyver.fenxlib.extensions.tuktukfx.bindings.TaskAbortBinding.taskAbortObservesShutdown hook
 * ensures that when the application is terminated, the flag is set so the background task can exit gracefully.
 */
public class FileSystemWatchTaskFactory {
    /**
     * Factory to create a FileSystemWatchTask
     * @param context the context to use
     * @return the task
     */
    public static JavaFxAdapter createScanTask(FileSystemWatchTaskContext context) {
        FileSystemWatchTask sourceScanTask = new FileSystemWatchTask(context);
        JavaFxAdapter adapter = new JavaFxAdapter<>(sourceScanTask);
        taskAbortObservesShutdown(adapter);
        return adapter;
    }
}
