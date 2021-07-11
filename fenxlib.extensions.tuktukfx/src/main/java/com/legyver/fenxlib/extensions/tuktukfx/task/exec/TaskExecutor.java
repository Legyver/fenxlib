package com.legyver.fenxlib.extensions.tuktukfx.task.exec;

import com.legyver.tuktukfx.adapter.AbortableTaskStatusAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Internal thread pool for executing (long-running) tasks.
 * For brief tasks that just need to execute in a subsequent pulse, use {@link javafx.application.Platform#runLater(Runnable)}
 */
public enum TaskExecutor {
	/**
	 * The singleton instance
	 */
	INSTANCE;
	private static final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	/**
	 * Submit a test to the task scheduler
	 * @param abortableTask the task
	 */
	public void submitTask(AbortableTaskStatusAdapter abortableTask) {
		pool.submit(abortableTask);
	}

	/**
	 * Shuts down the internal thread pool
	 */
	public void shutdownNow() {
		pool.shutdownNow();
	}
}
