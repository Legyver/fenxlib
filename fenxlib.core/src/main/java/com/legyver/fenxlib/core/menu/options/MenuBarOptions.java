package com.legyver.fenxlib.core.menu.options;

import javafx.event.EventTarget;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Grouping of event targets to be included in a menu-bar region
 */
public class MenuBarOptions<T extends EventTarget> {
	private final List<T> menuBarTargets;

	/**
	 * Construct a container for Menus that share an orientation, ie: left-orientated, right-orientated, etc
	 * @param menuBarTargets event targets to be included in a menu bar that share an orientation
	 */
	public MenuBarOptions(T...menuBarTargets) {
		this.menuBarTargets = menuBarTargets == null ? Collections.EMPTY_LIST : Arrays.asList(menuBarTargets);
	}

	/**
	 * Get the menu bar targets
	 * @return the menu bar targets
	 */
	public List<T> getMenuBarTargets() {
		return menuBarTargets;
	}
}
