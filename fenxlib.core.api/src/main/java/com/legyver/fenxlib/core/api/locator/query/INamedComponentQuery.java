package com.legyver.fenxlib.core.api.locator.query;

import javafx.scene.Node;

/**
 * Query that matches a registered component by name
 * @param <T> the type of the component
 */
public interface INamedComponentQuery<T extends Node> {

	/**
	 * Get the query string
	 * @return the query string
	 */
	String getQueryString();
}
