package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ButtonBar;

/**
 * Factory to create a ButtonBar control
 */
public class ButtonBarFactory implements NodeFactory<ButtonBar> {

    @Override
    public ButtonBar makeNode(LocationContext locationContext) throws CoreException {
        ButtonBar button = makeButtonBar();
        Fenxlib.register(locationContext, button);
        return button;
    }

    /**
     * Factory method to instantiate a ButtonBar.
     * @return a javafx ButtonBar by default, override if you need something else
     */
    protected ButtonBar makeButtonBar() {
        return new ButtonBar();
    }
}
