package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.TilePane;

/**
 * Factory to create a {@link TilePane}
 */
public class TilePaneFactory implements NodeFactory<TilePane> {
    @Override
    public TilePane makeNode(LocationContext locationContext) throws CoreException {
        TilePane pane = makeTilePane();
        Fenxlib.register(locationContext, pane);
        return pane;
    }

    /**
     * Factory method to instantiate a plain JavaFX TilePane.
     * @return a JavaFX TilePane, override if you want something else
     */
    protected TilePane makeTilePane() {
        return new TilePane();
    }
}
