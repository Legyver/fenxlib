package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.scene.layout.options.TilePaneOptions;
import javafx.scene.layout.TilePane;

/**
 * Factory to create a {@link TilePane}
 */
public class TilePaneFactory implements NodeFactory<TilePane, TilePaneOptions> {

    @Override
    public TilePane makeNode(LocationContext locationContext, TilePaneOptions options) throws CoreException {
        TilePane pane = makeTilePane();
        Fenxlib.register(locationContext.decorateWith(options.getName()), pane);
        return pane;
    }

    @Override
    public TilePaneOptions newOptions() {
        return new TilePaneOptions();
    }

    /**
     * Factory method to instantiate a plain JavaFX TilePane.
     * @return a JavaFX TilePane, override if you want something else
     */
    protected TilePane makeTilePane() {
        return new TilePane();
    }
}
