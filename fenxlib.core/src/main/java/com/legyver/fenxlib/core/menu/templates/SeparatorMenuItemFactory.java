package com.legyver.fenxlib.core.menu.templates;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.menu.factory.IMenuItemFactory;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Factory to add a separator a menu
 */
public class SeparatorMenuItemFactory implements IMenuItemFactory<MenuItem, MenuItemOptions> {

	/**
	 * Factory method to instantiate a SeparatorMenuItem
	 * @return the JavaFX control Separator MenuItem
	 */
	protected MenuItem makeSeparatorMenuItem() {
		return new SeparatorMenuItem();
	}

	@Override
	public MenuItem makeNode(LocationContext locationContext, MenuItemOptions menuItemOptions) throws CoreException {
		return makeSeparatorMenuItem();
	}

	@Override
	public MenuItemOptions newOptions() {
		return new MenuItemOptions();
	}
}
