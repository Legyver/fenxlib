package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

/**
 * Wrapping factory to embed the nested factories output in an HBox, but with growable spacers between each node
 */
public class SpreadHBoxFactory extends AbstractWrappingFactory<Node> implements SpaceableFactory, StyleableFactory<HBox> {

	/**
	 * Construct a factory to embed the content produced by the wrapped factories in an HBox
	 * @param factories the factories producing the HBox content
	 */
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
