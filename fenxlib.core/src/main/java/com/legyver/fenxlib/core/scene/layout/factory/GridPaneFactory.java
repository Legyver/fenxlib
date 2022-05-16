package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.GridPane;

/**
 * Factory to create a {@link GridPane}
 */
public class GridPaneFactory implements NodeFactory<GridPane> {
    @Override
    public GridPane makeNode(LocationContext locationContext) throws CoreException {
        GridPane pane = makeGridPane();
        Fenxlib.register(locationContext, pane);
        return pane;
    }

    /**
     * Factory method to instantiate a plain JavaFX GridPane.
     * @return a JavaFX GridPane, override if you want something else
     */
    protected GridPane makeGridPane() {
        return new GridPane();
    }
}
