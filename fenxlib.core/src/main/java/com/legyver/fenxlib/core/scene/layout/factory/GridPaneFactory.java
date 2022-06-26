package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.scene.layout.options.GridPaneOptions;
import javafx.scene.layout.GridPane;

/**
 * Factory to create a {@link GridPane}
 */
public class GridPaneFactory implements NodeFactory<GridPane, GridPaneOptions> {

    @Override
    public GridPane makeNode(LocationContext locationContext, GridPaneOptions options) throws CoreException {
        GridPane pane = makeGridPane();
        Fenxlib.register(locationContext.decorateWith(options.getName()), pane);
        return pane;
    }

    @Override
    public GridPaneOptions newOptions() {
        return new GridPaneOptions();
    }

    /**
     * Factory method to instantiate a plain JavaFX GridPane.
     * @return a JavaFX GridPane, override if you want something else
     */
    protected GridPane makeGridPane() {
        return new GridPane();
    }
}
