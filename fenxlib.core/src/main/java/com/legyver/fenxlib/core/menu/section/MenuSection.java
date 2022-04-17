package com.legyver.fenxlib.core.menu.section;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.menu.factory.IMenuItemFactory;

import java.util.List;

/**
 * Section of a menu.
 * @param <T> the type of menu section
 */
public interface MenuSection<T extends MenuSectionOptions> {

	/**
	 * Return the list of factories for all menu items that will be contained in this section.
	 * @param menuSectionOptions the options for the menu section
	 * @return all factories that produce nodes for this menu section.
	 * @throws CoreException if there is a problem creating the menu section factories
	 */
	List<? extends IMenuItemFactory> getFactories(T menuSectionOptions) throws CoreException;
}
