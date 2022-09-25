package com.legyver.fenxlib.core.event.handlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;

/**
 * Port setOnContextMenuRequested to right-click mouse events on nodes that do not support the latter
 */
public class ShowContextMenuOnRightClick implements EventHandler<MouseEvent> {

    private final Node triggerNode;
    private final ContextMenu contextMenuToShow;

    /**
     * Construct an event handler to show the context menu on a node
     * @param triggerNode the node to use as anchor
     * @param contextMenuToShow the context menu to show
     */
    public ShowContextMenuOnRightClick(Node triggerNode, ContextMenu contextMenuToShow) {
        this.triggerNode = triggerNode;
        this.contextMenuToShow = contextMenuToShow;
    }

    @Override
    public void handle(MouseEvent event) {
       if (event.isSecondaryButtonDown()) {//right-click
           event.consume();
           contextMenuToShow.show(triggerNode, event.getScreenX(), event.getScreenY());
       }
    }
}
