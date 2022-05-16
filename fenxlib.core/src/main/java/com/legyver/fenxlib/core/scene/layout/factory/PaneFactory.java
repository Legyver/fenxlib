package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.Pane;

/**
 * Factory to create a {@link Pane}
 */
public class PaneFactory implements NodeFactory<Pane> {
    @Override
    public Pane makeNode(LocationContext locationContext) throws CoreException {
        Pane pane = makePane();
        Fenxlib.register(locationContext, pane);
        return pane;
    }

    /**
     * Factory method to instantiate a plain JavaFX Pane.
     * @return a JavaFX Pane, override if you want something else
     */
    protected Pane makePane() {
        return new Pane();
    }
}
