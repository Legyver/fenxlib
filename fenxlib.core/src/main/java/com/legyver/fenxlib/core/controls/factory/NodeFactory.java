package com.legyver.fenxlib.core.controls.factory;

import javafx.scene.Node;

/**
 * Interface that all Factories must extend if constructing a subtype of {@link Node}
 * @param <T> the type of the widget
 */
public interface NodeFactory<T extends Node> extends StyleableFactory<T> {
	
}
