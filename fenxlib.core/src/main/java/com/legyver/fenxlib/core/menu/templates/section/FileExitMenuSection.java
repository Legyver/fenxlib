package com.legyver.fenxlib.core.menu.templates.section;

import com.legyver.fenxlib.core.menu.section.MenuSection;
import com.legyver.fenxlib.core.menu.templates.ExitMenuItemAction;
import com.legyver.fenxlib.core.menu.options.FileExitMenuOption;

import java.util.Arrays;

/**
 * Menu section containing an Exit menu option factory.  See {@link ExitMenuItemAction}
 */
public class FileExitMenuSection extends AbstractMenuSection implements MenuSection {

	/**
	 * Construct a MenuSection that contains a menu item to exit the application
	 * The property for the label is "legyver.defaults.label.menu.file.exit"
	 */
	public FileExitMenuSection() {
		super(Arrays.asList(new FileExitMenuOption("legyver.defaults.label.menu.file.exit")));
	}
}
