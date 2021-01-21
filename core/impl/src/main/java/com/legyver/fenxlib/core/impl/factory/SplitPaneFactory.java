package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import java.util.List;

import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Wrapper factory to embed the nested factories output into a SplitPane.
 * Only the first two nested factories will be rendered
 */
public class SplitPaneFactory extends AbstractWrappingFactory<Region> implements NodeFactory<SplitPane> {

	private final double topPercentage;

	public SplitPaneFactory(double topPercentage, NodeFactory<? extends Region>... factories) {
		super(factories);
		this.topPercentage = topPercentage;
	}

	@Override
	public SplitPane makeNode(LocationContext lc) throws CoreException {
		List<Region> nodes = super.makeChildren(lc);

		SplitPane splitPane = new SplitPane(nodes.toArray(new Node[nodes.size()]));
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setDividerPositions(topPercentage);

		if (!nodes.isEmpty()) {
			Region firstNode = (Region) nodes.get(0);
			VBox.setVgrow(firstNode, Priority.NEVER);
			//Constrain max size of top component:
			firstNode.maxHeightProperty().bind(splitPane.heightProperty().multiply(topPercentage));
			firstNode.minWidthProperty().bind(splitPane.maxWidthProperty());
		}

		if (nodes.size() == 2) {
			Region secondNode = (Region) nodes.get(1);
			VBox.setVgrow(secondNode, Priority.ALWAYS);
		}
		return splitPane;
	}

}
