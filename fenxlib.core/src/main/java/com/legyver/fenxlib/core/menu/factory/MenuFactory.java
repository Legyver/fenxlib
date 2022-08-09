package com.legyver.fenxlib.core.menu.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.regions.ApplicationRegions;
import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.MenuOptions;
import javafx.scene.control.Menu;

/**
 * Factory to create a menu
 */
public class MenuFactory implements StyleableFactory<Menu, MenuOptions> {

	@Override
	public Menu makeNode(LocationContext locationContext, MenuOptions options) throws CoreException {
		Menu menu = new Menu(options.getText());
		if (locationContext == null || locationContext.getName() == null) {
			locationContext = new DefaultLocationContext(ApplicationRegions.MENUS.getName());
		}

		ApplicationContext.getComponentRegistry().register(locationContext.decorateWith(options.getName()), menu);
		return menu;
	}

	@Override
	public MenuOptions newOptions() {
		return new MenuOptions();
	}

	/**
	 * Instantiate the menu.
	 * @return a plain javafx menu.  Override if you want something else.
	 */
	protected Menu makeMenu() {
		return new Menu();
	}
}
