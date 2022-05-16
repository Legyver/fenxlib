package com.legyver.fenxlib.core.menu.templates;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.menu.factory.IMenuItemFactory;
import javafx.css.Styleable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Factory to add a separator a menu
 */
public class SeparatorMenuItemFactory implements IMenuItemFactory {

	/**
	 * Factory method to instantiate a SeparatorMenuItem
	 * @return the JavaFX control Separator MenuItem
	 */
	protected MenuItem makeSeparatorMenuItem() {
		return new SeparatorMenuItem();
	}

	@Override
	public Styleable makeNode(LocationContext locationContext) throws CoreException {
		return makeSeparatorMenuItem();
	}
}
