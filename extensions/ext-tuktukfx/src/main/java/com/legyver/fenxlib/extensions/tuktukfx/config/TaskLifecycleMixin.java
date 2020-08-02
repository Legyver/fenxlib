package com.legyver.fenxlib.extensions.tuktukfx.config;

import com.legyver.fenxlib.core.config.options.mixins.HookRegistrationMixin;

/**
 * Mixin for shutting down the thread pool on application exit
 */
public interface TaskLifecycleMixin {

	/**
	 * In your {@link com.legyver.fenxlib.core.config.options.ApplicationOptions.Builder} call mixinLifecycleHook(shutDownThreadPoolOnExit())
	 */
	default HookRegistrationMixin shutDownThreadPoolOnExit() {
		return new TaskExecutorShutdownHook();
	}
}
