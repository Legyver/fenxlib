package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.scene.layout.options.AnchorPaneOptions;
import javafx.scene.layout.AnchorPane;

/**
 * Factory to create a {@link AnchorPane}
 */
public class AnchorPaneFactory implements NodeFactory<AnchorPane, AnchorPaneOptions> {

    @Override
    public AnchorPane makeNode(LocationContext locationContext, AnchorPaneOptions options) throws CoreException {
        AnchorPane anchorPane = makeAnchorPane();
        Fenxlib.register(locationContext.decorateWith(options.getName()), anchorPane);
        return anchorPane;
    }

    @Override
    public AnchorPaneOptions newOptions() {
        return new AnchorPaneOptions();
    }

    /**
     * Factory method to instantiate a plain JavaFX AnchorPane.
     * @return a JavaFX AnchorPane, override if you want something else
     */
    protected AnchorPane makeAnchorPane() {
        return new AnchorPane();
    }
}
