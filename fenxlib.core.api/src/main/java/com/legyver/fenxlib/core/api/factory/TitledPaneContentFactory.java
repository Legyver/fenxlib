package com.legyver.fenxlib.core.api.factory;

import javafx.scene.layout.Pane;

/**
 * Instantiate the content of a {@link Pane}
 * @param <T> the type of the pane to embed the content in
 */
public interface TitledPaneContentFactory<T extends Pane> extends NodeFactory<T> {

}
