package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ContextMenuOptions;
import javafx.scene.control.ContextMenu;

/**
 * Factory to create a ContextMenu control
 */
public class ContextMenuFactory implements StyleableFactory<ContextMenu, ContextMenuOptions> {

    @Override
    public ContextMenu makeNode(LocationContext locationContext, ContextMenuOptions options) throws CoreException {
        ContextMenu contextMenu = makeContextMenu();
        Fenxlib.register(locationContext.decorateWith(options.getName()), contextMenu);
        return contextMenu;
    }

    @Override
    public ContextMenuOptions newOptions() {
        return new ContextMenuOptions();
    }

    /**
     * Factory method to instantiate a ContextMenu.
     * @return a javafx ContextMenu by default, override if you need something else
     */
    protected ContextMenu makeContextMenu() {
        return new ContextMenu();
    }
}
