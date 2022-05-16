package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Factory to create a SeparatorMenuItem control
 */
public class SeparatorMenuItemFactory implements StyleableFactory<SeparatorMenuItem> {
    @Override
    public SeparatorMenuItem makeNode(LocationContext locationContext) throws CoreException {
        SeparatorMenuItem separator = makeSeparatorMenuItem();
        Fenxlib.register(locationContext, separator);
        return separator;
    }

    /**
     * Factory method to instantiate a SeparatorMenuItem.
     * @return a javafx SeparatorMenuItem by default, override if you need something else
     */
    protected SeparatorMenuItem makeSeparatorMenuItem() {
        return new SeparatorMenuItem();
    }
}
