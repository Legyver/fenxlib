package com.legyver.fenxlib.core.impl.factory.menu;

/**
 * Container for MenuFactory[] factories to be used for constructing a menu
 */
public class AbstractMenuOptions {
	private final MenuFactory[] factories;

	/**
	 * Construct a container for MenuFactory[] factories to be used for constructing a menu
	 * @param factories factories to use
	 */
	public AbstractMenuOptions(MenuFactory[] factories) {
		this.factories = factories;
	}

	/**
	 * Get the factories
	 * @return the factories
	 */
	public MenuFactory[] getFactories() {
		return factories;
	}

}
