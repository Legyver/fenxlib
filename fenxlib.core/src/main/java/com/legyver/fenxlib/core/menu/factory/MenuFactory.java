package com.legyver.fenxlib.core.menu.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;
import javafx.scene.control.Menu;

/**
 * Factory to create a menu
 */
public class MenuFactory implements StyleableFactory<Menu> {
	/**
	 * Default region name for any menus.  Used in programmatically selecting menus/menu items
	 */
	public static final String REGION_NAME = "menu";
	/**
	 * Construct param to pass the name to the factory in the map
	 */
	public static final String PARAM_NAME = "name";
	private final String name;

	/**
	 * Construct a factory to create a menu with the menu items provided by the wrapped factories
	 * @param name the name of the menu
	 */
	public MenuFactory(String name) {
		this.name = name;
	}

	@Override
	public Menu makeNode(LocationContext locationContext) throws CoreException {
		Menu menu = new Menu(name);
		if (locationContext == null || locationContext.getName() == null) {
			locationContext = new DefaultLocationContext(REGION_NAME);
		}

		LocationContextDecorator decorator = new LocationContextDecorator(locationContext);
		decorator.setName(name);

		ApplicationContext.getComponentRegistry().register(decorator, menu);
		return menu;
	}

	/**
	 * Instantiate the menu.
	 * @return a plain javafx menu.  Override if you want something else.
	 */
	protected Menu makeMenu() {
		return new Menu();
	}
}
