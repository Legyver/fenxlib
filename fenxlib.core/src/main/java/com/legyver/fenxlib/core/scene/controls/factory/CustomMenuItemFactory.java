package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.CustomMenuItem;

/**
 * Factory to create a CustomMenuItem control
 */
public class CustomMenuItemFactory implements StyleableFactory<CustomMenuItem> {
    @Override
    public CustomMenuItem makeNode(LocationContext locationContext) throws CoreException {
        CustomMenuItem customMenuItem = makeCustomMenuItem();
        Fenxlib.register(locationContext, customMenuItem);
        return customMenuItem;
    }

    /**
     * Factory method to instantiate a CustomMenuItem.
     * @return a javafx CustomMenuItem by default, override if you need something else
     */
    protected CustomMenuItem makeCustomMenuItem() {
        return new CustomMenuItem();
    }
}
