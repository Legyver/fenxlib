package com.legyver.fenxlib.core.impl.factory.menu.file.proto;

import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
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
	 */
	List<? extends IMenuItemFactory> getFactories(T menuSectionOptions);
}
