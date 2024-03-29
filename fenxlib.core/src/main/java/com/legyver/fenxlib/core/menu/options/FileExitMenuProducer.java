package com.legyver.fenxlib.core.menu.options;

import com.legyver.fenxlib.core.menu.templates.ExitMenuItemAction;

/**
 * Option for a Menu item that exits the application
 */
public class FileExitMenuProducer extends AbstractMenuItemProducer {

    /**
     * Construct an option for a menu item to exit the application
     * @param text the text for the menu item
     */
    public FileExitMenuProducer(String text) {
        super(text, new ExitMenuItemAction());
    }
}
