package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.layout.options.BorderPaneOptions;
import javafx.scene.layout.BorderPane;

/**
 * Factory to create a {@link BorderPane}
 */
public class BorderPaneFactory implements NodeFactory<BorderPane, BorderPaneOptions> {

    @Override
    public BorderPane makeNode(LocationContext locationContext, BorderPaneOptions options) throws CoreException {
        BorderPane pane = makeBorderPane();
        Fenxlib.register(locationContext.decorateWith(options.getName()), pane);
        return pane;
    }

    @Override
    public BorderPaneOptions newOptions() {
        return new BorderPaneOptions();
    }

    /**
     * Factory method to instantiate a plain JavaFX BorderPane.
     * @return a JavaFX BorderPane, override if you want something else
     */
    protected BorderPane makeBorderPane() {
        return new BorderPane();
    }
}
