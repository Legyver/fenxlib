package com.legyver.fenxlib.factory.menu;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuFactory {
	private final String name;
	private final IMenuItemFactory[] factories;

	public MenuFactory(String name, IMenuItemFactory... factories) {
		this.name = name;
		this.factories = factories;
	}

	public Menu makeMenu() {
		Menu menu = new Menu(name);
		if (factories != null) {
			List<MenuItem> items = Stream.of(factories)
					.map(this::makeMenuItem)
					.collect(Collectors.toList());
			menu.getItems().addAll(items);
		}
		return menu;
	}

	private MenuItem makeMenuItem(IMenuItemFactory factory) {
		return factory.makeItem();
	}

}
