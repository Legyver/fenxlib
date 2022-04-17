package com.legyver.fenxlib.core.event.handlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.ContextMenuEvent;

/**
 * Event handler to show a context menu on a given node
 */
public class ShowContextMenuEventHandler implements EventHandler<ContextMenuEvent> {
    private final Node triggerNode;
    private final ContextMenu contextMenuToShow;

    /**
     * Construct an event handler to show the context menu on a node
     * @param triggerNode the node to use as anchor
     * @param contextMenuToShow the context menu to show
     */
    public ShowContextMenuEventHandler(Node triggerNode, ContextMenu contextMenuToShow) {
        this.triggerNode = triggerNode;
        this.contextMenuToShow = contextMenuToShow;
    }

    @Override
    public void handle(ContextMenuEvent event) {
        event.consume();
        contextMenuToShow.show(triggerNode, event.getScreenX(), event.getScreenY());
    }
}
