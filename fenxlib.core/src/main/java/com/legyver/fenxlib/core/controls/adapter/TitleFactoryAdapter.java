package com.legyver.fenxlib.core.controls.adapter;

import com.legyver.core.exception.CoreException;

import com.legyver.fenxlib.core.controls.factory.ITitleFactory;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.Node;

/**
 * Adapt any NodeFactory to an {@link ITitleFactory}
 */
public class TitleFactoryAdapter<T extends Node> implements ITitleFactory<T> {
	private final NodeFactory<T> wrappedFactory;

	/**
	 * Construct a factory adaptor to apply the output of a wrapped factory to the title
	 * @param wrappedFactory the factory to wrap and adapt
	 */
	public TitleFactoryAdapter(NodeFactory<T> wrappedFactory) {
		this.wrappedFactory = wrappedFactory;
	}

	@Override
	public T makeNode(LocationContext locationContext) throws CoreException {
		return wrappedFactory.makeNode(locationContext);
	}
}
