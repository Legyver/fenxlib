package com.legyver.fenxlib.core.menu.section;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.MenuItem;

import java.util.List;

/**
 * Section of a menu.
 */
public interface MenuSection {

	/**
	 * Return the list of factories for all menu items that will be contained in this section.
	 * @param locationContext the location context for the menu
	 * @return all factories that produce nodes for this menu section.
	 * @throws CoreException if there is a problem creating the menu section factories
	 */
	List<MenuItem> makeMenuItems(LocationContext locationContext) throws CoreException;
}
