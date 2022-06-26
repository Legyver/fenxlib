package com.legyver.fenxlib.core.menu.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.scene.controls.options.MenuItemOptions;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * Factory to construct a menu item
 */
public class MenuItemFactory implements IMenuItemFactory<MenuItem, MenuItemOptions> {

    @Override
    public MenuItem makeNode(LocationContext locationContext, MenuItemOptions options) throws CoreException {
        MenuItem menuItem = makeMenuItem();
        menuItem.setText(options.getText());
        menuItem.setOnAction(options.getEventHandler());

        Fenxlib.register(locationContext.decorateWith(options.getName()), menuItem);
        return menuItem;
    }

    @Override
    public MenuItemOptions newOptions() {
        return new MenuItemOptions();
    }


    /**
     * Factory method for instantiating a menu item
     * @return plain javafx {@link MenuItem} by default, override if you want something else
     */
    protected MenuItem makeMenuItem() {
        return new MenuItem();
    }

}
