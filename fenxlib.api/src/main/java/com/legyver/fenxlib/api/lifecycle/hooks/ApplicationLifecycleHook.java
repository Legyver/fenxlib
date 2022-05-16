package com.legyver.fenxlib.api.lifecycle.hooks;

import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;

/**
 * Register a piece of executable code to be ran at a point in the application lifecycle
 */
public interface ApplicationLifecycleHook {

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

	/**
	 *
	 * @return true if this implementation should usurp other similary-named services
	 */
	default boolean usurp() {
		return false;
	}

	/**
	 * Get the name of this service
	 * @return the name of this service, used for usurping similarly-named services
	 */
	String getName();
}
