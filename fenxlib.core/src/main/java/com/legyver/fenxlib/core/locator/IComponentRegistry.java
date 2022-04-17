package com.legyver.fenxlib.core.locator;

import javafx.event.EventTarget;

/**
 * Registry of all registered components in the application
 */
public interface IComponentRegistry {

	/**
	 * Register a component with the registry
	 * @param context the context specifying the register-as information
	 * @param target the node to be registered
	 * @param <T> the type of the JavaFX component
	 */
	default <T extends EventTarget> void register(LocationContext context, T target) {
		register(context, target, false);
	}

	/**
	 * Register a component with the registry.
	 * Note: if typeOnly is false, then any node currently sharing the same location context will be overwritten by this target.
	 * @param context the context specifying the register-as information
	 * @param target the node to be registered
	 * @param <T> the type of the JavaFX component
	 * @param typedOnly flag for registering just as type
	 */
	<T extends EventTarget> void register(LocationContext context, T target, boolean typedOnly);

	/**
	 * Remove the registration for component at the specified location
	 * @param context the context specifying the registered-as information
	 */
	void deregister(LocationContext context);
}
