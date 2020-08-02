package com.legyver.fenxlib.core.config.options.mixins;

import com.legyver.fenxlib.core.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;

/**
 * Register a piece of executable code to be ran at a point in the application lifecycle
 */
public interface HookRegistrationMixin {

	/**
	 *
	 * @return LifecyclePhase of the application to register the hook to be executed in
	 */
	LifecyclePhase getLifecyclePhase();

	/**
	 *
	 * @return ExecutableHook to execute
	 */
	ExecutableHook getExecutableHook();

	/**
	 *
	 * @return The int priority that acts as an order tie-breaker within an application LifecyclePhase
	 */
	default int getPriority() {
		return 500;
	}
}
