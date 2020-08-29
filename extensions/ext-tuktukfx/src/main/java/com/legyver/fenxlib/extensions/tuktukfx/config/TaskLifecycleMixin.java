package com.legyver.fenxlib.extensions.tuktukfx.config;

import com.legyver.fenxlib.core.config.options.init.ApplicationLifecycleHook;

/**
 * Mixin for shutting down the thread pool on application exit
 */
public interface TaskLifecycleMixin {

	/**
	 * In your ? extends {@link com.legyver.fenxlib.core.config.options.ApplicationOptions.Builder} call mixinLifecycleHook(shutDownThreadPoolOnExit())
	 */
	default ApplicationLifecycleHook shutDownThreadPoolOnExit() {
		return new TaskExecutorShutdownApplicationLifecycleHook();
	}
}
