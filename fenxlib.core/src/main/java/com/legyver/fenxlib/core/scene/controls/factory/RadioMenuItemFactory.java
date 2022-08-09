package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.RadioMenuItemOptions;
import javafx.scene.control.RadioMenuItem;

/**
 * Factory to create a RadioMenuItem control
 */
public class RadioMenuItemFactory implements StyleableFactory<RadioMenuItem, RadioMenuItemOptions> {

    @Override
    public RadioMenuItem makeNode(LocationContext locationContext, RadioMenuItemOptions options) throws CoreException {
        RadioMenuItem radioMenuItem = makeRadioMenuItem();
        Fenxlib.register(locationContext.decorateWith(options.getName()), radioMenuItem);
        return radioMenuItem;
    }

    @Override
    public RadioMenuItemOptions newOptions() {
        return new RadioMenuItemOptions();
    }

    /**
     * Factory method to instantiate a RadioMenuItem.
     * @return a javafx RadioMenuItem by default, override if you need something else
     */
    protected RadioMenuItem makeRadioMenuItem() {
        return new RadioMenuItem();
    }
}
