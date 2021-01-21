package com.legyver.fenxlib.core.impl.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import javafx.scene.Node;

import static com.legyver.fenxlib.core.impl.util.GuidUtil.initGuid;

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
