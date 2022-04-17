package com.legyver.fenxlib.core.menu.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.Fenxlib;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * Factory to construct a menu item
 */
public class MenuItemFactory implements IMenuItemFactory {
    /**
     * Constructor param when passing the menu item name via a map
     */
    public static final String PARAM_NAME = "name";
    /**
     * Constructor param when passing the menu item event handler via a map
     */
    public static final String PARAM_ACTION = "action";

    private final String name;
    private final EventHandler<ActionEvent> action;

    /**
     * Construct a factory to create a menu item
     * @param name the name of the menu item
     * @param action the action to take place when menu item is selected
     */
    public MenuItemFactory(String name, EventHandler<ActionEvent> action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public Styleable makeNode(LocationContext locationContext) throws CoreException {
        MenuItem menuItem = makeMenuItem();
        menuItem.setText(name);
        menuItem.setOnAction(action);
        LocationContextDecorator decorator = new LocationContextDecorator(locationContext);
        decorator.setName(name);
        Fenxlib.register(decorator, menuItem);
        return menuItem;
    }


    /**
     * Factory method for instantiating a menu item
     * @return plain javafx {@link MenuItem} by default, override if you want something else
     */
    protected MenuItem makeMenuItem() {
        return new MenuItem();
    }
}
