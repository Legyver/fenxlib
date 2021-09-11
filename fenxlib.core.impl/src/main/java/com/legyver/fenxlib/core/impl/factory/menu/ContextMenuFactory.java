package com.legyver.fenxlib.core.impl.factory.menu;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

/**
 * Factory to create a menu
 */
public class ContextMenuFactory {
	/**
	 * Region name suffix for any context menu.  Used in programmatically selecting context menu items
	 * ie: area_contextmenu, item_contextmenu, etc
	 */
	public static final String REGION_SUFFIX = "_contextmenu";
	private final String name;
	private final IMenuItemFactory[] factories;

	/**
	 * Construct a factory to create a context menu with the menu items provided by the wrapped factories
	 * @param name the name of the context menu
	 * @param factories any factories that produce context menu items to be included in the menu
	 */
	public ContextMenuFactory(String name, IMenuItemFactory... factories) {
		this.name = name + REGION_SUFFIX;
		this.factories = factories;
	}

	/**
	 * Construct a menu with the menu items produced by all wrapped factories
	 * @param parentContext the parent context for the context menu
	 * @return the Menu
	 * @throws CoreException if there is an error creating any of the menu items
	 */
	public ContextMenu makeMenu(LocationContext parentContext) throws CoreException {
		ContextMenu menu = new ContextMenu();
		LocationContextDecorator decorator = new LocationContextDecorator(parentContext);
		decorator.setName(name);

		ApplicationContext.getComponentRegistry().register(decorator, menu);
		if (factories != null) {
			List<MenuItem> items = unwrap(() -> Stream.of(factories)
					.map(f -> wrap(() -> f.makeItem(decorator)))
					.collect(Collectors.toList()));
			menu.getItems().addAll(items);
		}
		return menu;
	}

}
