package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.FlowPane;

/**
 * Factory to create a {@link FlowPane}
 */
public class FlowPaneFactory implements NodeFactory<FlowPane> {
    @Override
    public FlowPane makeNode(LocationContext locationContext) throws CoreException {
        FlowPane pane = makeFlowPane();
        Fenxlib.register(locationContext, pane);
        return pane;
    }

    /**
     * Factory method to instantiate a plain JavaFX FlowPane.
     * @return a JavaFX FlowPane, override if you want something else
     */
    protected FlowPane makeFlowPane() {
        return new FlowPane();
    }
}
