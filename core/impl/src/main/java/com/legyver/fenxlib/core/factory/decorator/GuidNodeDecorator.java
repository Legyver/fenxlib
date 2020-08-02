package com.legyver.fenxlib.core.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.NodeFactory;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.Node;

import static com.legyver.fenxlib.core.util.GuidUtil.initGuid;

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
