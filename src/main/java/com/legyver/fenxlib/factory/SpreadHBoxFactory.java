package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import javafx.scene.layout.HBox;
import com.legyver.fenxlib.locator.LocationContext;
import java.util.List;
import javafx.scene.Node;

public class SpreadHBoxFactory extends AbstractWrappingFactory<Node> implements NodeFactory<HBox>, SpaceableFactory {

	public SpreadHBoxFactory(NodeFactory... factories) {
		super(factories);
	}

	@Override
	public HBox makeNode(LocationContext locationContext) throws CoreException {
		List<Node> nodes = super.makeChildren(locationContext);
		HBox spaced = spaceNodes(nodes.toArray(new Node[nodes.size()]));
		spaced.getStyleClass().add("spaced");
		return spaced;
	}

}
