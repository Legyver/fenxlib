package com.legyver.fenxlib.core.factory;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public interface SpaceableFactory {

	default HBox spaceNodes(Node... nodes) {
		HBox result = new HBox();
		if (nodes != null) {
			for (int i = 0; i < nodes.length; i++) {
				Node node = nodes[i];
				Region spacer = getSpacer(node);
				if (i < nodes.length / 2) {
					result.getChildren().add(node);
					result.getChildren().add(spacer);
				} else if (i > nodes.length / 2) {
					result.getChildren().add(spacer);
					result.getChildren().add(node);
				} else {
					result.getChildren().add(node);
				}
			}
		}
		return result;
	}

	default Region getSpacer(Node node) {
		Region spacer = new Region();
		spacer.getStyleClass().add(node.getStyle());
		HBox.setHgrow(spacer, Priority.SOMETIMES);
		return spacer;
	}
}
