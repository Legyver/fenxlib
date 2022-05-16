package com.legyver.fenxlib.extensions.tuktukfx.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.extensions.tuktukfx.task.exec.TaskExecutor;

/**
 * Application lifecycle hook mixin to shutdown the {@link TaskExecutor}
 */
public class TaskExecutorShutdownApplicationLifecycleHook implements ApplicationLifecycleHook {
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
			if (delayInMillis > 0) {
				try {
					Thread.sleep(delayInMillis);
				} catch (InterruptedException e) {
					throw new CoreException("Error sleeping thread", e);
				}
			}
			TaskExecutor.INSTANCE.shutdownNow();//shutdown thread-pool;
		};
	}

	@Override
	public String getName() {
		return TaskExecutorShutdownApplicationLifecycleHook.class.getName();
	}
}
