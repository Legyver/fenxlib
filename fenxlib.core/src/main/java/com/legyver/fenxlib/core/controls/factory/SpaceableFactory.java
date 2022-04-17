package com.legyver.fenxlib.core.controls.factory;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Mixin factory to space out nodes across the available area within a TextBox
 */
public interface SpaceableFactory {

	/**
	 * Space out the indicated nodes in a text box that has growable regions between each node
	 * @param nodes the nodes to space out
	 * @return text box containing the spaced out nodes
	 */
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

	/**
	 * Create a spacer with a style-class matching a specified node
	 * @param node the node to obtain the style-class from
	 * @return the region
	 */
	default Region getSpacer(Node node) {
		return getSpacer(node.getStyle());
	}

	/**
	 * Create a spacer with a style-class matching a specified node
	 * @param style style-class to use
	 * @return the region
	 */
	default Region getSpacer(String style) {
		Region spacer = new Region();
		spacer.getStyleClass().add(style);
		HBox.setHgrow(spacer, Priority.SOMETIMES);
		return spacer;
	}
}
