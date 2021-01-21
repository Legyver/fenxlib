package com.legyver.fenxlib.core.impl.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import javafx.scene.Node;

public class CssClassDecorator<T extends Node> implements NodeFactory<T> {
	private final String cssClass;
	private final NodeFactory<T> factory;

	public CssClassDecorator(String cssClass, NodeFactory<T> factory) {
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
