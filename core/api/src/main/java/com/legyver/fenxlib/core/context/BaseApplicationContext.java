package com.legyver.fenxlib.core.context;

import com.legyver.fenxlib.core.locator.query.DefaultComponentRegistry;
import com.legyver.fenxlib.core.locator.query.QueryableComponentRegistry;

public class BaseApplicationContext {
	/**
	 * Allow binding factories to bind with supported BindableAppState properties
	 */
	private static final BindableAppState appState = new BindableAppState();

	/**
	 * All factories register their components here on construction.
	 */
	private static final QueryableComponentRegistry componentRegistry = new DefaultComponentRegistry();

	public static BindableAppState getAppState() {
		return appState;
	}

	public static QueryableComponentRegistry getComponentRegistry() {
		return componentRegistry;
	}
}
