package com.legyver.fenxlib.core.api.context;

import com.legyver.fenxlib.core.api.locator.query.DefaultComponentRegistry;
import com.legyver.fenxlib.core.api.locator.query.QueryableComponentRegistry;

/**
 * Base ApplicationContext that has the minimum static information required by the framework
 */
public class BaseApplicationContext {
	/**
	 * Allow binding factories to bind with supported BindableAppState properties
	 */
	private static final BindableAppState appState = new BindableAppState();

	/**
	 * All factories register their components here on construction.
	 */
	private static final QueryableComponentRegistry componentRegistry = new DefaultComponentRegistry();

	/**
	 * Get the state of the application
	 * @return the application state
	 */
	public static BindableAppState getAppState() {
		return appState;
	}

	/**
	 * Get the registry of all registered components of the application
	 * @return the component registry
	 */
	public static QueryableComponentRegistry getComponentRegistry() {
		return componentRegistry;
	}
}
