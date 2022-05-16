package com.legyver.fenxlib.core.controls.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.Node;

/**
 * Decorate a node with a specified style
 * @param <T> the type of the node
 */
public class StyleDecorator <T extends Node> implements StyleableFactory<T> {
    private final String style;
    private final NodeFactory<T> factory;

    /**
     * Construct a factory to decorate the output of the wrapped factory with a style
     * @param style the style to apply
     * @param factory the factory creating the node
     */
    public StyleDecorator(String style, NodeFactory<T> factory) {
        this.style = style;
        this.factory = factory;
    }

    @Override
    public T makeNode(LocationContext locationContext) throws CoreException {
        T node = factory.makeNode(locationContext);
        node.setStyle(style);
        return node;
    }

    /**
     * Factory to decorate a node with italics styling
     * @param <U> the type of the node
     */
    public static class Italics<U extends Node> extends StyleDecorator<U> {

        /**
         * Construct a factory to decorate the constructed node with italics styling
         * @param factory factory creating the node
         */
        public Italics(NodeFactory<U> factory) {
            super("-fx-font-style: italic", factory);
        }
    }
}
