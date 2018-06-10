package com.legyver.fenxlib.factory.menu;

import com.legyver.core.exception.CoreException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class MenuFactory {
	private final String name;
	private final IMenuItemFactory[] factories;

	public MenuFactory(String name, IMenuItemFactory... factories) {
		this.name = name;
		this.factories = factories;
	}

	public Menu makeMenu() throws CoreException {
		Menu menu = new Menu(name);
		if (factories != null) {
			List<MenuItem> items = unwrap(() -> Stream.of(factories)
					.map(f -> wrap(() -> f.makeItem()))
					.collect(Collectors.toList()));
			menu.getItems().addAll(items);
		}
		return menu;
	}

}
