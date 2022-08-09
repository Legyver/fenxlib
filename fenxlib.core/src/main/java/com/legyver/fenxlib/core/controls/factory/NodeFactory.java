package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.scene.Node;

/**
 * Interface that all Factories must extend if constructing a subtype of {@link Node}
 * @param <T> the type of the widget
 */
public interface NodeFactory<T extends Node, U extends StyleableControlOptions<T>> extends StyleableFactory<T, U> {

}
