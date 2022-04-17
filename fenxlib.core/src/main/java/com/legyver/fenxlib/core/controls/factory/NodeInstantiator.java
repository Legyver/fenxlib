package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import javafx.scene.Node;

/**
 * Instantiate a widget of a subtype of Node
 * @param <T> the type of the widget
 */
public interface NodeInstantiator<T extends Node> {

	/**
	 * Create the new instance of the control
	 * @return the control
	 * @throws CoreException if there is an error constructing the control
	 */
	T newInstance() throws CoreException;
}
