package com.legyver.fenxlib.core.impl.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import javafx.scene.Node;

public class StyleDecorator <T extends Node> implements NodeFactory<T> {
    private final String style;
    private final NodeFactory<T> factory;

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

    public static class Italics<U extends Node> extends StyleDecorator<U> {

        public Italics(NodeFactory<U> factory) {
            super("-fx-font-style: italic", factory);
        }
    }
}
