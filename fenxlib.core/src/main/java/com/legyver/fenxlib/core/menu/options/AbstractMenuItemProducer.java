package com.legyver.fenxlib.core.menu.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * Super class for a description of a menu item to produced
 */
public class AbstractMenuItemProducer {
    /**
     * The text for the menu item
     */
    protected final String text;
    private final EventHandler<ActionEvent> eventHandler;

    /**
     * Construct an option for a menu item with the specified label and event handler
     * @param text the text to display on the menu option
     * @param eventHandler the handler that fires when the menu item is selected
     */
    protected AbstractMenuItemProducer(String text, EventHandler<ActionEvent> eventHandler) {
        this.text = text;
        this.eventHandler = eventHandler;
    }

    /**
     * Make a menu item
     * @param locationContext the location context to use when registering the menu item
     * @return the menu item
     * @throws CoreException if there is an error producing the menu item
     */
    public MenuItem makeMenuItem(LocationContext locationContext) throws CoreException {
        return ControlsFactory.make(MenuItem.class, locationContext, new MenuItemOptions()
                .text(text)
                .eventHandler(eventHandler)
        );
    }
}
