package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import javafx.scene.layout.HBox;
import com.legyver.fenxlib.locator.LocationContext;

public class SpreadHBoxFactory implements NodeFactory<HBox>, SpaceableFactory {

	private final NodeFactory leftFactory;
	private final NodeFactory rightFactory;

	public SpreadHBoxFactory(NodeFactory leftFactory, NodeFactory rightFactory) {
		this.leftFactory = leftFactory;
		this.rightFactory = rightFactory;
	}

	@Override
	public HBox makeNode(LocationContext locationContext) throws CoreException {
		HBox spaced = spaceNodes(leftFactory.makeNode(locationContext), rightFactory.makeNode(locationContext));
		spaced.getStyleClass().add("spaced");
		return spaced;
	}

}
