package com.legyver.fenxlib.core.impl.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import javafx.scene.Node;

/**
 * Factory to auto-register the constructed node
 * @param <T> the type of the node
 */
public class RegisterAsDecorator<T extends Node> implements NodeFactory<T> {

	private final NodeFactory<T> factory;
	private final String registerAs;

	/**
	 * Construct a factory to auto-register the output of the factory as a specific name
	 * @param factory the factory creating the node
	 * @param registerAs the name to use when registering the node
	 */
	public RegisterAsDecorator(NodeFactory<T> factory, String registerAs) {
		this.factory = factory;
		this.registerAs = registerAs;
	}

	@Override
	public T makeNode(LocationContext locationContext) throws CoreException {
		T node = factory.makeNode(locationContext);
		LocationContext decoratedLC = new LocationContextDecorator(locationContext);
		decoratedLC.setName(registerAs);
		ApplicationContext.getComponentRegistry().register(decoratedLC, node);
		return node;
	}

}
