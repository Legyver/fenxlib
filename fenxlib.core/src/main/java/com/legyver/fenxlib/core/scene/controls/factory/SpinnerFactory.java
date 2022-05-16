package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.Spinner;

/**
 * Factory to create a Spinner control
 */
public class SpinnerFactory implements NodeFactory<Spinner> {
    @Override
    public Spinner makeNode(LocationContext locationContext) throws CoreException {
        Spinner scrollPane = makeSpinner();
        Fenxlib.register(locationContext, scrollPane);
        return scrollPane;
    }

    /**
     * Factory method to instantiate a Spinner.
     * @return a javafx Spinner by default, override if you need something else
     */
    protected Spinner makeSpinner() {
        return new Spinner();
    }
}
