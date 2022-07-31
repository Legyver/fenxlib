package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ToolBarOptions;
import javafx.scene.control.ToolBar;

/**
 * Factory to create a ToolBar control
 */
public class ToolBarFactory implements StyleableFactory<ToolBar, ToolBarOptions> {

    @Override
    public ToolBar makeNode(LocationContext locationContext, ToolBarOptions options) throws CoreException {
        ToolBar toolBar = makeToolBar();
        Fenxlib.register(locationContext.decorateWith(options.getName()), toolBar);
        return toolBar;
    }

    @Override
    public ToolBarOptions newOptions() {
        return new ToolBarOptions();
    }

    /**
     * Factory method to instantiate a Toolbar.
     * @return a javafx Toolbar by default, override if you need something else
     */
    protected ToolBar makeToolBar() {
        return new ToolBar();
    }
}
