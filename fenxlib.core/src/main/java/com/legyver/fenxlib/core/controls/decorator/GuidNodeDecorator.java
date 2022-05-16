package com.legyver.fenxlib.core.controls.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.util.GuidUtil;
import javafx.scene.Node;

/**
 * Factory to decorate the node created by the wrapped factory with a UUID.
 * The guid is set as a property on the node.  See {@link GuidUtil#initGuid}
 * @param <T> the type of the node
 */
public class GuidNodeDecorator<T extends Node> implements StyleableFactory<T> {
	private final NodeFactory<T> factory;

	/**
	 * Decorator for a node factory that will apply a GUID to the output of the factory
	 * @param factory the factory being decorated
	 */
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
