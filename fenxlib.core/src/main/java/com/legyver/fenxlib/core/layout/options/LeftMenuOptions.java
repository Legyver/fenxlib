package com.legyver.fenxlib.core.layout.options;

import com.legyver.fenxlib.core.menu.factory.MenuFactory;
import com.legyver.fenxlib.core.menu.options.AbstractMenuOptions;

/**
 * Options for the left menus
 */
public class LeftMenuOptions extends AbstractMenuOptions {

	/**
	 * Construct options describing the menus to be left-aligned as produced by wrapped factories
	 * @param factories the factories producing the menus
	 */
	public LeftMenuOptions(MenuFactory... factories) {
		super(factories);
	}

}
