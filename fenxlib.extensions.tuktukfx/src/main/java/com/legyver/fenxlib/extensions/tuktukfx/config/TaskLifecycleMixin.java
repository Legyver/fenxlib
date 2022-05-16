package com.legyver.fenxlib.extensions.tuktukfx.config;

import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;

/**
 * Mixin for shutting down the thread pool on application exit
 */
public interface TaskLifecycleMixin {

	/**
	 * In your ApplicationOptions.Builder call registerLifecycleHook(shutDownThreadPoolOnExit())
	 * @return the lifecycle hook to shut down the thread pool on application exit
	 */
	default ApplicationLifecycleHook shutDownThreadPoolOnExit() {
		return new TaskExecutorShutdownApplicationLifecycleHook();
	}
}
