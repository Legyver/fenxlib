package com.legyver.fenxlib.core.controls.factory;

import javafx.scene.Node;

/**
 * Adapter factory to apply the output of another factory to a title
 * @param <T> type of node
 */
public interface ITitleFactory<T extends Node> extends StyleableFactory<T> {
}
