package com.legyver.fenxlib.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.NodeFactory;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.LocationContextDecorator;
import com.legyver.fenxlib.util.GuiUtil;
import javafx.scene.Node;

public class RegisterAsDecorator<T extends Node> implements NodeFactory<T> {

	private final NodeFactory<T> factory;
	private final String registerAs;

	public RegisterAsDecorator(NodeFactory<T> factory, String registerAs) {
		this.factory = factory;
		this.registerAs = registerAs;
	}

	@Override
	public T makeNode(LocationContext locationContext) throws CoreException {
		T node = factory.makeNode(locationContext);
		LocationContext decoratedLC = new LocationContextDecorator(locationContext);
		decoratedLC.setName(registerAs);
		GuiUtil.getComponentRegistry().register(decoratedLC, node);
		return node;
	}

}
