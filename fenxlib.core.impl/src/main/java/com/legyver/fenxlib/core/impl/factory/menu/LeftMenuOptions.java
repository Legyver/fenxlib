package com.legyver.fenxlib.core.impl.factory.menu;

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
