package com.legyver.fenxlib.extensions.tuktukfx.task.exec;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.extensions.tuktukfx.config.TaskExecutorShutdownApplicationLifecycleHook;
import com.legyver.tuktukfx.adapter.AbortableTaskStatusAdapter;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.*;

/**
 * Internal thread pool for executing (long-running) tasks.
 * The thread-pool is defaulted to one less than the number of available cores
 * For brief tasks that just need to execute in a subsequent pulse, use {@link javafx.application.Platform#runLater(Runnable)}
 */
public class TaskExecutor {
	/**
	 * The singleton instance
	 */
	private static final TaskExecutor taskExecutor = new TaskExecutor();
	private static final ExecutorService pool = new ContextClearingThreadPoolExecutor(Runtime.getRuntime().availableProcessors() - 1);
	private TaskExecutorShutdownApplicationLifecycleHook hook;
	private boolean shutdownInitiated;

	private TaskExecutor() {
		hook = new TaskExecutorShutdownApplicationLifecycleHook();
		ApplicationContext.getApplicationLifecycleHookRegistry().registerHook(hook.getLifecyclePhase(), hook.getExecutableHook(), hook.getPriority());
	}

	/**
	 * Get the singleton instance of the task executor
	 * @return the instance
	 */
	public static TaskExecutor getInstance() {
		return taskExecutor;
	}

	/**
	 * Allow for configuration of the executor
	 * @return the configuration
	 */
	public TaskExecutorConfiguration configure() {
		return new TaskExecutorConfiguration();
	}

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
	public synchronized void shutdownNow() {
		if (!shutdownInitiated) {
			pool.shutdownNow();
			shutdownInitiated = true;
		}
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

	/**
	 * Configuration for the task executor
	 */
	public class TaskExecutorConfiguration {
		/**
		 * Delay the shutdown of the threadpool for a custom period.
		 * If not specified this will not delay at all, which may result in InterruptedException if your code uses semaphores.
		 * @param millis the duration to wait before shutting down the thread pool.
		 * @return this configuration
		 */
		public TaskExecutorConfiguration delayShutdown(long millis) {
			ApplicationContext.getApplicationLifecycleHookRegistry().delayShutdown(millis);
			return this;
		}
	}
}
