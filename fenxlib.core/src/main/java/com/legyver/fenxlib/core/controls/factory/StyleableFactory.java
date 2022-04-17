package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.css.Styleable;

/**
 * Create a styleable widget.
 * Use when the widget is not also a subtype of {@link javafx.scene.Node}
 * @param <T> the type of the widgets
 */
public interface StyleableFactory<T extends Styleable> {
	/**
	 * Create a styleable node
	 * @param locationContext the location context to register the widget as
	 * @return the constructed widget
	 * @throws CoreException if there is an error
	 */
	T makeNode(LocationContext locationContext) throws CoreException;
}
