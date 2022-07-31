package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.layout.options.StackPaneOptions;
import javafx.scene.layout.StackPane;

/**
 * Factory to create a {@link StackPane}
 */
public class StackPaneFactory implements NodeFactory<StackPane, StackPaneOptions> {

    @Override
    public StackPane makeNode(LocationContext locationContext, StackPaneOptions options) throws CoreException {
        StackPane pane = makeStackPane();
        Fenxlib.register(locationContext.decorateWith(options.getName()), pane);
        return pane;
    }

    @Override
    public StackPaneOptions newOptions() {
        return new StackPaneOptions();
    }

    /**
     * Factory method to instantiate a plain JavaFX StackPane.
     * @return a JavaFX StackPane, override if you want something else
     */
    protected StackPane makeStackPane() {
        return new StackPane();
    }
}
