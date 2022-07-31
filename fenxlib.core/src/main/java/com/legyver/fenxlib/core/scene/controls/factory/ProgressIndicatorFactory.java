package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ProgressIndicatorOptions;
import javafx.scene.control.ProgressIndicator;

/**
 * Factory to create a ProgressIndicator control
 */
public class ProgressIndicatorFactory implements NodeFactory<ProgressIndicator, ProgressIndicatorOptions> {

    @Override
    public ProgressIndicator makeNode(LocationContext locationContext, ProgressIndicatorOptions options) throws CoreException {
        ProgressIndicator progressIndicator = makeProgressIndicator();
        Fenxlib.register(locationContext.decorateWith(options.getName()), progressIndicator);
        return progressIndicator;
    }

    @Override
    public ProgressIndicatorOptions newOptions() {
        return new ProgressIndicatorOptions();
    }

    /**
     * Factory method to instantiate a ProgressIndicator.
     * @return a javafx ProgressIndicator by default, override if you need something else
     */
    protected ProgressIndicator makeProgressIndicator() {
        return new ProgressIndicator();
    }

}
