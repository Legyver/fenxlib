package com.legyver.fenxlib.core.impl.factory.menu;

import com.legyver.core.exception.CoreException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

/**
 * Factory to create a menu
 */
public class MenuFactory {
	/**
	 * Default region name for any menus.  Used in programmatically selecting menus/menu items
	 */
	public static final String REGION_NAME = "menu";
	private final String name;
	private final IMenuItemFactory[] factories;

	/**
	 * Construct a factory to create a menu with the menu items provided by the wrapped factories
	 * @param name the name of the menu
	 * @param factories any factories that produce menu items to be included in the menu
	 */
	public MenuFactory(String name, IMenuItemFactory... factories) {
		this.name = name;
		this.factories = factories;
	}

	/**
	 * Construct a menu with the menu items produced by all wrapped factories
	 * @return the Menu
	 * @throws CoreException if there is an error creating any of the menu items
	 */
	public Menu makeMenu() throws CoreException {
		Menu menu = new Menu(name);
		LocationContext locationContext = new DefaultLocationContext(REGION_NAME);
		LocationContextDecorator decorator = new LocationContextDecorator(locationContext);
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
