package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ContextMenu;

/**
 * Factory to create a ContextMenu control
 */
public class ContextMenuFactory implements StyleableFactory<ContextMenu> {
    @Override
    public ContextMenu makeNode(LocationContext locationContext) throws CoreException {
        ContextMenu contextMenu = makeContextMenu();
        Fenxlib.register(locationContext, contextMenu);
        return contextMenu;
    }

    /**
     * Factory method to instantiate a ContextMenu.
     * @return a javafx ContextMenu by default, override if you need something else
     */
    protected ContextMenu makeContextMenu() {
        return new ContextMenu();
    }
}
