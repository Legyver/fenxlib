package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ButtonBarOptions;
import javafx.scene.control.ButtonBar;

/**
 * Factory to create a ButtonBar control
 */
public class ButtonBarFactory implements NodeFactory<ButtonBar, ButtonBarOptions> {

    @Override
    public ButtonBar makeNode(LocationContext locationContext, ButtonBarOptions options) throws CoreException {
        ButtonBar button = makeButtonBar();
        Fenxlib.register(locationContext.decorateWith(options.getName()), button);
        return button;
    }

    @Override
    public ButtonBarOptions newOptions() {
        return new ButtonBarOptions();
    }

    /**
     * Factory method to instantiate a ButtonBar.
     * @return a javafx ButtonBar by default, override if you need something else
     */
    protected ButtonBar makeButtonBar() {
        return new ButtonBar();
    }

}
