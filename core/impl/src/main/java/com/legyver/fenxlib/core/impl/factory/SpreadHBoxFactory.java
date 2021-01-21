package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.layout.HBox;

import java.util.List;
import javafx.scene.Node;

/**
 * Wrapping factory to embed the nested factories output in an HBox, but with growable spacers between each node
 */
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
