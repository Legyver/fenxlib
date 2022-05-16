package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.AnchorPane;

/**
 * Factory to create a {@link AnchorPane}
 */
public class AnchorPaneFactory implements NodeFactory<AnchorPane> {
    @Override
    public AnchorPane makeNode(LocationContext locationContext) throws CoreException {
        AnchorPane anchorPane = makeAnchorPane();
        Fenxlib.register(locationContext, anchorPane);
        return anchorPane;
    }

    /**
     * Factory method to instantiate a plain JavaFX AnchorPane.
     * @return a JavaFX AnchorPane, override if you want something else
     */
    protected AnchorPane makeAnchorPane() {
        return new AnchorPane();
    }
}
