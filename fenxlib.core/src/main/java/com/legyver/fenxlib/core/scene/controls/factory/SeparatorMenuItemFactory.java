package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.scene.controls.options.SeparatorMenuItemOptions;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Factory to create a SeparatorMenuItem control
 */
public class SeparatorMenuItemFactory implements StyleableFactory<SeparatorMenuItem, SeparatorMenuItemOptions> {

    @Override
    public SeparatorMenuItem makeNode(LocationContext locationContext, SeparatorMenuItemOptions options) throws CoreException {
        SeparatorMenuItem separator = makeSeparatorMenuItem();
        Fenxlib.register(locationContext.decorateWith(options.getName()), separator);
        return separator;
    }

    @Override
    public SeparatorMenuItemOptions newOptions() {
        return new SeparatorMenuItemOptions();
    }

    /**
     * Factory method to instantiate a SeparatorMenuItem.
     * @return a javafx SeparatorMenuItem by default, override if you need something else
     */
    protected SeparatorMenuItem makeSeparatorMenuItem() {
        return new SeparatorMenuItem();
    }
}
