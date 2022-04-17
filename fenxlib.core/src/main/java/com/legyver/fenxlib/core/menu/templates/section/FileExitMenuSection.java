package com.legyver.fenxlib.core.menu.templates.section;

import com.legyver.fenxlib.core.menu.factory.IMenuItemFactory;
import com.legyver.fenxlib.core.menu.section.MenuSection;
import com.legyver.fenxlib.core.menu.section.MenuSectionOptions;
import com.legyver.fenxlib.core.menu.templates.ExitMenuItemFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Menu section containing an Exit menu option factory.  See {@link ExitMenuItemFactory}
 */
public class FileExitMenuSection implements MenuSection {

	@Override
	public List<IMenuItemFactory> getFactories(MenuSectionOptions menuSectionOptions) {
		return Arrays.asList(new ExitMenuItemFactory("Exit"));
	}

}
