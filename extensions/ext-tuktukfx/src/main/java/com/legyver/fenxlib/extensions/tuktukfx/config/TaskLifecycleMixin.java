package com.legyver.fenxlib.extensions.tuktukfx.config;

import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;

/**
 * Mixin for shutting down the thread pool on application exit
 */
public interface TaskLifecycleMixin {

	/**
	 * In your &lt;? extends {@link com.legyver.fenxlib.core.impl.config.options.ApplicationOptions.Builder}&gt; call registerLifecycleHook(shutDownThreadPoolOnExit())
	 */
	default ApplicationLifecycleHook shutDownThreadPoolOnExit() {
		return new TaskExecutorShutdownApplicationLifecycleHook();
	}
}
