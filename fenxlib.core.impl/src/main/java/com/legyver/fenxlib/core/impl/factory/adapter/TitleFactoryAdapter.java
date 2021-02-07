package com.legyver.fenxlib.core.impl.factory.adapter;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.ITitleFactory;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import javafx.scene.Node;

/**
 * Adapt any NodeFactory to a {@link ITitleFactory}
 */
public class TitleFactoryAdapter<T extends Node> implements ITitleFactory<T> {
	private final NodeFactory<T> wrappedFactory;

	public TitleFactoryAdapter(NodeFactory<T> wrappedFactory) {
		this.wrappedFactory = wrappedFactory;
	}

	@Override
	public T makeNode(LocationContext locationContext) throws CoreException {
		return wrappedFactory.makeNode(locationContext);
	}
}
