package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class VBoxFactory extends AbstractWrappingFactory<Node> implements NodeFactory<VBox> {

	public VBoxFactory(NodeFactory... nodeFactories) {
		super(nodeFactories);
	}

	public VBox makeVBox(LocationContext locationContext) throws CoreException {
		List<Node> entries = makeChildren(locationContext);
		return new VBox(entries.toArray(new Node[entries.size()]));
	}

	@Override
	public VBox makeNode(LocationContext locationContext) throws CoreException {
		return makeVBox(locationContext);
	}
}
