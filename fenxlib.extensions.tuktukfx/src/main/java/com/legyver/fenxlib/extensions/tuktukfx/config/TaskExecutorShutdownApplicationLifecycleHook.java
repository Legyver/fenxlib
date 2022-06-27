package com.legyver.fenxlib.extensions.tuktukfx.config;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.context.BindableAppState;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.api.logging.LazyLog;
import com.legyver.fenxlib.extensions.tuktukfx.task.exec.TaskExecutor;
import javafx.application.Platform;

/**
 * Application lifecycle hook mixin to shutdown the {@link TaskExecutor}
 */
public class TaskExecutorShutdownApplicationLifecycleHook implements ApplicationLifecycleHook {
	private static final LazyLog logger = new LazyLog(TaskExecutorShutdownApplicationLifecycleHook.class);
	private final long delayInMillis;

	/**
	 * Construct an application lifecycle hook to shut down the {@link TaskExecutor} after a given delay
	 * @param delayInMillis delay in milliseconds before shutting down the thread pool.
	 *                      If the delay is &lt; 1, the thread pool is shut down immediately
	 */
	public TaskExecutorShutdownApplicationLifecycleHook(long delayInMillis) {
		this.delayInMillis = delayInMillis;
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
			new ShutDownhook(delayInMillis).run();
		};
	}

	@Override
	public String getName() {
		return TaskExecutorShutdownApplicationLifecycleHook.class.getName();
	}

	private static class ShutDownhook implements Runnable {
		private final Long delayInMillis;
		private final BindableAppState appState;
		private Long tangoInMillis;
		private boolean executed;

		private ShutDownhook(Long delayInMillis) {
			this.delayInMillis = delayInMillis;
			this.appState = ApplicationContext.getAppState();
		}

		@Override
		public void run() {
			if (!executed) {
				if (appState.isShuttingDown()) {
					if (tangoInMillis == null) {
						logger.info("Application is shutting down.  Starting delay of " + delayInMillis + "ms");
						//only start the delay when the status has been updated
						tangoInMillis = System.currentTimeMillis() + delayInMillis;
						Platform.runLater(this);
					} else if (tangoInMillis < System.currentTimeMillis()) {
						logger.info("Executing delayed shutdown of thread pool");
						TaskExecutor.INSTANCE.shutdownNow();
						logger.info("Thread pool shutdown complete");
						executed = true;
					} else {
						logger.trace("tango [" + tangoInMillis + "], current time [" + System.currentTimeMillis() + "]");
						Platform.runLater(this);
					}
				} else {
					logger.debug("Application not yet shutting down");
					Platform.runLater(this);
				}
			}
		}
	}
}
