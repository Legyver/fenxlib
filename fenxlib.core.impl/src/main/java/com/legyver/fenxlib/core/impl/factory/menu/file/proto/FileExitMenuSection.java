package com.legyver.fenxlib.core.impl.factory.menu.file.proto;

import com.legyver.fenxlib.core.impl.factory.menu.ExitMenuItemFactory;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;

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
