package com.legyver.fenxlib.core.api.locator;

import javafx.event.EventTarget;

/**
 * Registry of all registered components in the application
 */
public interface IComponentRegistry {

	/**
	 * Register a component with the registry
	 * @param context the context specifying the register-as information
	 * @param target the node to be registered
	 */
	<T extends EventTarget> void register(LocationContext context, T target);

	/**
	 * Remove the registration for component at the specified location
	 * @param context the context specifying the registered-as information
	 */
	void deregister(LocationContext context);
}
