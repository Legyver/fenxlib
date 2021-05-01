package com.legyver.fenxlib.core.impl.factory.menu;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.control.MenuItem;

/**
 * Type for factory that creates a menu item
 */
public interface IMenuItemFactory {

	/**
	 * Create a menu item
	 * @param locationContext The location context to use
	 * @return the menu item
	 * @throws CoreException if there is an error
	 */
	MenuItem makeItem(LocationContext locationContext) throws CoreException;
}
