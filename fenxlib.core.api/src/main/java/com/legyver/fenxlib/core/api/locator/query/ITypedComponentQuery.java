package com.legyver.fenxlib.core.api.locator.query;

import javafx.scene.Node;

/**
 * Query for a component using the type of the component
 * @param <T> the type of the component
 */
public interface ITypedComponentQuery<T extends Node> {

	/**
	 * Get the query string
	 * @return the query string
	 */
	String getQueryString();

	/**
	 * Get the type of the component to query for
	 * @return the type of the component
	 */
	Class getType();
}
