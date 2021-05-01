package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.factory.NodeFactory;
import javafx.scene.Node;

/**
 * Adapter factory to apply the output of another factory to a title
 * @param <T> type of node
 */
public interface ITitleFactory<T extends Node> extends NodeFactory<T> {
}
