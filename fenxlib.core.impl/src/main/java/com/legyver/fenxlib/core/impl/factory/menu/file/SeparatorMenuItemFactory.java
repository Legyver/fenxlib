package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Factory to add a separator a menu
 */
public class SeparatorMenuItemFactory implements IMenuItemFactory {
	
	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		return new SeparatorMenuItem();
	}
}
