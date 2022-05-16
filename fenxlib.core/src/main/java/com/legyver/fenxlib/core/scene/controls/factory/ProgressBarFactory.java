package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ProgressBar;

/**
 * Factory to create a ProgressBar control
 */
public class ProgressBarFactory implements NodeFactory<ProgressBar> {
    @Override
    public ProgressBar makeNode(LocationContext locationContext) throws CoreException {
        ProgressBar progressBar = makeProgressBar();
        Fenxlib.register(locationContext, progressBar);
        return progressBar;
    }

    /**
     * Factory method to instantiate a ProgressBar.
     * @return a javafx ProgressBar by default, override if you need something else
     */
    protected ProgressBar makeProgressBar() {
        return new ProgressBar();
    }
}
