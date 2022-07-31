package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.SpinnerOptions;
import javafx.scene.control.Spinner;

/**
 * Factory to create a Spinner control
 */
public class SpinnerFactory implements NodeFactory<Spinner, SpinnerOptions> {

    @Override
    public Spinner makeNode(LocationContext locationContext, SpinnerOptions options) throws CoreException {
        Spinner scrollPane = makeSpinner();
        Fenxlib.register(locationContext.decorateWith(options.getName()), scrollPane);
        return scrollPane;
    }

    @Override
    public SpinnerOptions newOptions() {
        return new SpinnerOptions();
    }

    /**
     * Factory method to instantiate a Spinner.
     * @return a javafx Spinner by default, override if you need something else
     */
    protected Spinner makeSpinner() {
        return new Spinner();
    }
}
