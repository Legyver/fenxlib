package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.RadioMenuItem;

/**
 * Factory to create a RadioMenuItem control
 */
public class RadioMenuItemFactory implements StyleableFactory<RadioMenuItem> {
    @Override
    public RadioMenuItem makeNode(LocationContext locationContext) throws CoreException {
        RadioMenuItem radioMenuItem = makeRadioMenuItem();
        Fenxlib.register(locationContext, radioMenuItem);
        return radioMenuItem;
    }

    /**
     * Factory method to instantiate a RadioMenuItem.
     * @return a javafx RadioMenuItem by default, override if you need something else
     */
    protected RadioMenuItem makeRadioMenuItem() {
        return new RadioMenuItem();
    }
}
