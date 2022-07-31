package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ProgressBarOptions;
import javafx.scene.control.ProgressBar;

/**
 * Factory to create a ProgressBar control
 */
public class ProgressBarFactory implements NodeFactory<ProgressBar, ProgressBarOptions> {

    @Override
    public ProgressBar makeNode(LocationContext locationContext, ProgressBarOptions options) throws CoreException {
        ProgressBar progressBar = makeProgressBar();
        Fenxlib.register(locationContext.decorateWith(options.getName()), progressBar);
        return progressBar;
    }

    @Override
    public ProgressBarOptions newOptions() {
        return new ProgressBarOptions();
    }

    /**
     * Factory method to instantiate a ProgressBar.
     * @return a javafx ProgressBar by default, override if you need something else
     */
    protected ProgressBar makeProgressBar() {
        return new ProgressBar();
    }
}
