package com.legyver.fenxlib.core.api.factory;

import javafx.scene.Node;

/**
 * Instantiate a widget of a subtype of Node
 * @param <T> the type of the widget
 */
public interface NodeInstantiator<T extends Node> {

	/**
	 * Create the new instance of the widget
	 * @return the widget
	 */
	T newInstance();
}
