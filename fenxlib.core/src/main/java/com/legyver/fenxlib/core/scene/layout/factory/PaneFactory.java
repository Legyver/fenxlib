package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.scene.layout.options.PaneOptions;
import javafx.scene.layout.Pane;

/**
 * Factory to create a {@link Pane}
 */
public class PaneFactory implements NodeFactory<Pane, PaneOptions> {

    @Override
    public Pane makeNode(LocationContext locationContext, PaneOptions options) throws CoreException {
        Pane pane = makePane();
        Fenxlib.register(locationContext.decorateWith(options.getName()), pane);
        return pane;
    }

    @Override
    public PaneOptions newOptions() {
        return new PaneOptions();
    }

    /**
     * Factory method to instantiate a plain JavaFX Pane.
     * @return a JavaFX Pane, override if you want something else
     */
    protected Pane makePane() {
        return new Pane();
    }
}
