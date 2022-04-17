package com.legyver.fenxlib.core.locator.query;

import com.legyver.fenxlib.core.locator.IComponentRegistry;
import javafx.event.EventTarget;

/**
 * Queryable version of a ComponentRegistry
 * This allows a component to be queried by type or by name
 */
public interface QueryableComponentRegistry extends IComponentRegistry {

	/**
	 * Get the component identified by this type query
	 * @param query the type query
	 * @param <T> the type of the JavaFX Component
	 * @return the component
	 */
	<T extends EventTarget> T get(ITypedComponentQuery query);

	/**
	 * Get the component identified by the named locator in this query
	 * @param query the query to execute
	 * @param <T> the type of the JavaFX Component
	 * @return the node registered at that location.
	 */
	<T extends EventTarget> T get(INamedComponentQuery query);

}