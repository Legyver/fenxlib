package com.legyver.fenxlib.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.NodeFactory;
import com.legyver.fenxlib.locator.LocationContext;
import javafx.scene.Node;

import static com.legyver.fenxlib.util.GuidUtil.initGuid;

public class GuidNodeDecorator<T extends Node> implements NodeFactory<T> {
	private final NodeFactory<T> factory;

	public GuidNodeDecorator(NodeFactory<T> factory) {
		this.factory = factory;
	}

	@Override
	public T makeNode(LocationContext lc) throws CoreException {
		T node = factory.makeNode(lc);
		initGuid(node);
		return node;
	}

}
