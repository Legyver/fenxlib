package com.legyver.fenxlib.core.api.locator.query;

import com.legyver.fenxlib.core.api.locator.IComponentRegistry;
import javafx.event.EventTarget;
import javafx.scene.Node;

/**
 * Queryable version of a ComponentRegistry
 * This allows a component to be queried by type or by name
 */
public interface QueryableComponentRegistry extends IComponentRegistry {

	/**
	 * Get the component identified by this type query
	 * @param query the type query
	 * @return the component
	 */
	<T extends EventTarget> T get(ITypedComponentQuery query);

	/**
	 * Get the component identified by the named locator in this query
	 * @param query the query to execute
	 * @return the node registered at that location.
	 */
	<T extends EventTarget> T get(INamedComponentQuery query);

}
