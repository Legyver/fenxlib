package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ProgressIndicator;

/**
 * Factory to create a ProgressIndicator control
 */
public class ProgressIndicatorFactory implements NodeFactory<ProgressIndicator> {
    @Override
    public ProgressIndicator makeNode(LocationContext locationContext) throws CoreException {
        ProgressIndicator progressIndicator = makeProgressIndicator();
        Fenxlib.register(locationContext, progressIndicator);
        return progressIndicator;
    }

    /**
     * Factory method to instantiate a ProgressIndicator.
     * @return a javafx ProgressIndicator by default, override if you need something else
     */
    protected ProgressIndicator makeProgressIndicator() {
        return new ProgressIndicator();
    }
}
