package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.CustomMenuItemOptions;
import javafx.scene.control.CustomMenuItem;

/**
 * Factory to create a CustomMenuItem control
 */
public class CustomMenuItemFactory implements StyleableFactory<CustomMenuItem, CustomMenuItemOptions> {

    @Override
    public CustomMenuItem makeNode(LocationContext locationContext, CustomMenuItemOptions options) throws CoreException {
        CustomMenuItem customMenuItem = makeCustomMenuItem();
        Fenxlib.register(locationContext.decorateWith(options.getName()), customMenuItem);
        return customMenuItem;
    }

    @Override
    public CustomMenuItemOptions newOptions() {
        return new CustomMenuItemOptions();
    }

    /**
     * Factory method to instantiate a CustomMenuItem.
     * @return a javafx CustomMenuItem by default, override if you need something else
     */
    protected CustomMenuItem makeCustomMenuItem() {
        return new CustomMenuItem();
    }
}
