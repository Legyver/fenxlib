package com.legyver.fenxlib.extensions.tuktukfx.task.exec;

import com.legyver.tuktukfx.adapter.AbortableTaskStatusAdapter;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.*;

/**
 * Internal thread pool for executing (long-running) tasks.
 * The thread-pool is defaulted to one less than the number of available cores
 * For brief tasks that just need to execute in a subsequent pulse, use {@link javafx.application.Platform#runLater(Runnable)}
 */
public enum TaskExecutor {
	/**
	 * The singleton instance
	 */
	INSTANCE;
	private static final ExecutorService pool = new ContextClearingThreadPoolExecutor(Runtime.getRuntime().availableProcessors() - 1);

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

	private static class ContextClearingThreadPoolExecutor extends ThreadPoolExecutor {

		private ContextClearingThreadPoolExecutor(int nThreads) {
			super(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<>());
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			super.afterExecute(r, t);
			ThreadContext.clearAll();
		}
	}
}
