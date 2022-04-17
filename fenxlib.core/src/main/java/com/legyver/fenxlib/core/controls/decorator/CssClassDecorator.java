package com.legyver.fenxlib.core.controls.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.Node;

/**
 * Factory to decorate the output of a node with a css class.
 * @param <T> the type of the node
 */
public class CssClassDecorator<T extends Node> implements StyleableFactory<T> {
	private final String cssClass;
	private final StyleableFactory<T> factory;

	/**
	 * Construct a factory to decorate the nested factory output with a css class
	 * @param cssClass the css class to use
	 * @param factory the factory that's output will be decorated
	 */
	public CssClassDecorator(String cssClass, StyleableFactory<T> factory) {
		this.cssClass = cssClass;
		this.factory = factory;
	}
	
	@Override
	public T makeNode(LocationContext lc) throws CoreException {
		T node = factory.makeNode(lc);
		node.getStyleClass().add(cssClass);
		return node;
	}

}
