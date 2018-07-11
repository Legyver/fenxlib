package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import java.util.List;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import com.legyver.fenxlib.locator.LocationContext;

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
