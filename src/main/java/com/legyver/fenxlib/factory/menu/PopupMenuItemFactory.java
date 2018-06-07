package com.legyver.fenxlib.factory.menu;

import javafx.scene.control.MenuItem;

public class PopupMenuItemFactory implements IMenuItemFactory<MenuItemOptions> {
	private final String name;

	public PopupMenuItemFactory(String name) {
		this.name = name;
	}

	@Override
	public MenuItem makeItem() {
		MenuItem popup = new MenuItem(name);
		return popup;
	}
}
