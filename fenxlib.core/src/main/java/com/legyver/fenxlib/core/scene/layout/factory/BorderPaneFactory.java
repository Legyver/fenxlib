package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.BorderPane;

/**
 * Factory to create a {@link BorderPane}
 */
public class BorderPaneFactory implements NodeFactory<BorderPane> {
    @Override
    public BorderPane makeNode(LocationContext locationContext) throws CoreException {
        BorderPane pane = makeBorderPane();
        Fenxlib.register(locationContext, pane);
        return pane;
    }

    /**
     * Factory method to instantiate a plain JavaFX BorderPane.
     * @return a JavaFX BorderPane, override if you want something else
     */
    protected BorderPane makeBorderPane() {
        return new BorderPane();
    }
}
