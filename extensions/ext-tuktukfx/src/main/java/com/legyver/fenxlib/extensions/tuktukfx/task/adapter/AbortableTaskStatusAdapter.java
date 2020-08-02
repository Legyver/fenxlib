package com.legyver.fenxlib.extensions.tuktukfx.task.adapter;

import com.legyver.tuktukfx.adapter.TaskStatusAdapter;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

/**
 * {@link javafx.concurrent.Task#cancel()} doesn't actually set cancelled right away.
 * To abort loops, have your {@link com.legyver.tuktukfx.processor.TaskProcessor} monitor {@link #isAborted()}
 */
public interface AbortableTaskStatusAdapter extends TaskStatusAdapter, Runnable {
	boolean isAborted();
	void setAborted(boolean aborted);

	//Expose methods from javafx.concurrent.Task
	void setOnSucceeded(EventHandler<WorkerStateEvent> workerStateEvent);
	void setOnFailed(EventHandler<WorkerStateEvent> workerStateEvent);
	void setOnScheduled(EventHandler<WorkerStateEvent> workerStateEvent);
	void setOnRunning(EventHandler<WorkerStateEvent> workerStateEvent);
	void setOnCancelled(EventHandler<WorkerStateEvent> workerStateEvent);
}
