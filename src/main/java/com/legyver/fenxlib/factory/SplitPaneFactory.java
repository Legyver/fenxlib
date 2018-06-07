package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import com.legyver.fenxlib.locator.LocationContext;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class SplitPaneFactory implements NodeFactory<SplitPane> {

	private final NodeFactory<? extends Region>[] factories;
	private Region firstNode = null;
	private final double topPercentage;

	public SplitPaneFactory(double topPercentage, NodeFactory<? extends Region>... factories) {
		this.factories = factories;
		this.topPercentage = topPercentage;
	}

	@Override
	public SplitPane makeNode(LocationContext lc) throws CoreException {
		List<Node> nodes;
		if (factories == null) {
			nodes = Collections.EMPTY_LIST;
		} else {
			nodes = unwrap(() -> Stream.of(factories)
					.map(f -> wrap(() -> f.makeNode(lc)))
					.map(this::freezeHeight)
					.collect(Collectors.toList()));
		}
		SplitPane splitPane = new SplitPane(nodes.toArray(new Node[nodes.size()]));
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setDividerPositions(topPercentage);

		//Constrain max size of top component:
		firstNode.maxHeightProperty().bind(splitPane.heightProperty().multiply(topPercentage));
		if (nodes.size() == 2) {
			Region secondNode = (Region) nodes.get(1);
			VBox.setVgrow(secondNode, Priority.ALWAYS);
		}
		return splitPane;
	}

	private Region freezeHeight(Region original) {
		if (firstNode == null) {
			VBox.setVgrow(original, Priority.NEVER);
			firstNode = original;
		} else {
			HBox.setHgrow(original, Priority.SOMETIMES);
		}

		return original;
	}

}
