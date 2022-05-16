package com.legyver.fenxlib.core.menu.factory;


import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import javafx.scene.control.MenuItem;

/**
 * Abstract factory for producing MenuItems
 */
public abstract class AbstractMenuItemFactory {

	/**
	 * Register a {@link MenuItem} so it can be activated/deactivated according to custom application logic
	 * @param menuLocationContext the location context of the the MenuItem will be inserted
	 * @param menuItem the MenuItem
	 * @return the MenuItem
	 */
	protected MenuItem register(LocationContext menuLocationContext, MenuItem menuItem) {
		LocationContext decoratedLocationContext = new LocationContextDecorator(menuLocationContext);
		decoratedLocationContext.setName(getName());
		Fenxlib.register(menuLocationContext, menuItem);
		return menuItem;
	}

	/**
	 * The name of the menu item
	 * @return the menu item name
	 */
	protected abstract String getName();
}
