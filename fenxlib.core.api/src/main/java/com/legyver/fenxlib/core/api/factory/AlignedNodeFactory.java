package com.legyver.fenxlib.core.api.factory;

import javafx.scene.Node;

/**
 * Decorate a node factory with the alignment of it's output.
 * Example:
 *   - Given an HBox, any AlignedNodeFactory can specify if the output should be inserted from the leftmost available or the rightmost available.
 * @param <T> the type of the widget
 */
public interface AlignedNodeFactory<T extends Node> extends NodeFactory<T> {
    /**
     * Get the alignment to assign the produced node
     * @return the alignment
     */
    EnqueueAlignment getAlignment();

    /**
     * Alignment the node should be enqueued in an HBox
     */
    enum EnqueueAlignment {
        /**
         * The node should be added to the left side of the HBox
         */
        LEFT,
        /**
         * The node should be added to the right side of the HBox
         */
        RIGHT
    }
}
