package com.legyver.fenxlib.factory;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public interface SpaceableFactory {

	default HBox spaceNodes(Node left, Node right) {
		Region spacer = getSpacer(left);
		return new HBox(left, spacer, right);
	}

	default HBox spaceNodes(Node left, Node center, Node right) {
		Region spacer1 = getSpacer(left);
		Region spacer2 = getSpacer(right);
		return new HBox(left, spacer1, center, spacer2, right);
	}

	default Region getSpacer(Node node) {
		Region spacer = new Region();
		spacer.getStyleClass().add(node.getStyle());
		HBox.setHgrow(spacer, Priority.SOMETIMES);
		return spacer;
	}
}
