package com.legyver.fenxlib.extensions.tuktukfx.config;

import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.api.logging.LazyLog;
import com.legyver.fenxlib.extensions.tuktukfx.task.exec.TaskExecutor;

/**
 * Application lifecycle hook mixin to shut down the {@link TaskExecutor}
 */
public class TaskExecutorShutdownApplicationLifecycleHook implements ApplicationLifecycleHook {
	private static final LazyLog logger = new LazyLog(TaskExecutorShutdownApplicationLifecycleHook.class);

	/**
	 * Construct an application lifecycle hook to shut down the {@link TaskExecutor} after a given delay
	 * @param delayInMillis delay in milliseconds before shutting down the thread pool.
	 *                      If the delay is &lt; 1, the thread pool is shut down immediately
	 * @deprecated No longer used.  Will be removed in a following major release.
	 */
	@Deprecated
	public TaskExecutorShutdownApplicationLifecycleHook(long delayInMillis) {
	}

	/**
	 * Construct an application lifecycle hook to shut down the {@link TaskExecutor} immediately
	 */
	public TaskExecutorShutdownApplicationLifecycleHook() {
		this(0);
	}

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.SHUTDOWN;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			new ShutDownhook().run();
		};
	}

	/**
	 * Set the delay to wait before shutting down the thread pool
	 * @param delayInMillis the period to wait in milliseconds
	 * @deprecated No longer used.  Will be removed in a following major release
	 */
	@Deprecated
	public void setDelayInMillis(long delayInMillis) {
	}

	@Override
	public String getName() {
		return TaskExecutorShutdownApplicationLifecycleHook.class.getName();
	}

	private static class ShutDownhook implements Runnable {

		@Override
		public void run() {
			logger.info("Executing shutdown of thread pool");
			TaskExecutor.getInstance().shutdownNow();
			logger.info("Thread pool shutdown complete.");
		}
	}
}
