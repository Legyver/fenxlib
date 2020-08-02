package com.legyver.fenxlib.core.factory.menu.file.proto;

import com.legyver.fenxlib.core.factory.menu.ExitMenuItemFactory;
import com.legyver.fenxlib.core.factory.menu.IMenuItemFactory;
import java.util.Arrays;
import java.util.List;

public class FileExitMenuSection implements MenuSection {

	@Override
	public List<IMenuItemFactory> getFactories(MenuSectionOptions menuSectionOptions) {
		return Arrays.asList(new ExitMenuItemFactory("Exit"));
	}

}