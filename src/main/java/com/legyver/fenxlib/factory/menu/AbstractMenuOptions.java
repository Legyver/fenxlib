package com.legyver.fenxlib.factory.menu;

public class AbstractMenuOptions {
	private final MenuFactory[] factories;

	public AbstractMenuOptions(MenuFactory[] factories) {
		this.factories = factories;
	}

	public MenuFactory[] getFactories() {
		return factories;
	}

}
