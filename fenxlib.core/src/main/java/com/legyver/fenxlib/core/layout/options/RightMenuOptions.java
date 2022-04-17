package com.legyver.fenxlib.core.layout.options;


import com.legyver.fenxlib.core.menu.factory.MenuFactory;
import com.legyver.fenxlib.core.menu.options.AbstractMenuOptions;

/**
 * Options for the right menus
 */
public class RightMenuOptions extends AbstractMenuOptions {

	/**
	 * Identify all right-orientated Menus
	 * @param factories factories for producing right-orientated menus
	 */
	public RightMenuOptions(MenuFactory... factories) {
		super(factories);
	}

}
