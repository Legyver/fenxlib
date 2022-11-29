package com.legyver.fenxlib.core.menu.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.api.menu.IMenuItemFactory;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

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
			List<MenuItem> menuItems = new ArrayList<>(factories.length);
			for (IMenuItemFactory menuItemFactory : factories) {
				MenuItem menuItem = (MenuItem) menuItemFactory.makeNode(decorator, new MenuItemOptions());
				menuItems.add(menuItem);
			}
			menu.getItems().addAll(menuItems);
		}
		return menu;
	}

}
