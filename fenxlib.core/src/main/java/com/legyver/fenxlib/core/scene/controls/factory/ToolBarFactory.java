package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ToolBar;

/**
 * Factory to create a ToolBar control
 */
public class ToolBarFactory implements StyleableFactory<ToolBar> {
    @Override
    public ToolBar makeNode(LocationContext locationContext) throws CoreException {
        ToolBar toolBar = makeToolBar();
        Fenxlib.register(locationContext, toolBar);
        return toolBar;
    }

    /**
     * Factory method to instantiate a Toolbar.
     * @return a javafx Toolbar by default, override if you need something else
     */
    protected ToolBar makeToolBar() {
        return new ToolBar();
    }
}
