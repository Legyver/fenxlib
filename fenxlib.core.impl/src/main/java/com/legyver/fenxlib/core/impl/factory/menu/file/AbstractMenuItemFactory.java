package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.scene.control.MenuItem;

public abstract class AbstractMenuItemFactory {

	protected MenuItem register(LocationContext menuLocationContext, MenuItem menuItem) {
		LocationContext decoratedLocationContext = new LocationContextDecorator(menuLocationContext);
		decoratedLocationContext.setName(getName());
		ApplicationContext.getComponentRegistry().register(menuLocationContext, menuItem);
		return menuItem;
	}

	protected abstract String getName();
}
