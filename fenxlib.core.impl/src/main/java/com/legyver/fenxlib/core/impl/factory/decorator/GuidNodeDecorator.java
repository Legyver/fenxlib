package com.legyver.fenxlib.core.impl.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.util.GuidUtil;
import javafx.scene.Node;

/**
 * Factory to decorate the node created by the wrapped factory with a UUID.
 * The guid is set as a property on the node.  See {@link GuidUtil#initGuid}
 * @param <T> the type of the node
 */
public class GuidNodeDecorator<T extends Node> implements NodeFactory<T> {
	private final NodeFactory<T> factory;

	public GuidNodeDecorator(NodeFactory<T> factory) {
		this.factory = factory;
	}

	@Override
	public T makeNode(LocationContext lc) throws CoreException {
		T node = factory.makeNode(lc);
		GuidUtil.initGuid(node);
		return node;
	}

}
