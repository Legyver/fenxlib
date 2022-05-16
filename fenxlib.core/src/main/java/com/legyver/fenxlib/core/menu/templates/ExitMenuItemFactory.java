package com.legyver.fenxlib.core.menu.templates;

import com.legyver.fenxlib.core.event.correlation.CorrelatingEventHandlerFactory;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.HookExecutingAction;
import com.legyver.fenxlib.core.lifecycle.hooks.ShutdownHookDecorator;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.menu.factory.IMenuItemFactory;
import javafx.scene.control.MenuItem;

/**
 * Factory to create a File > Exit menu option
 */
public class ExitMenuItemFactory implements IMenuItemFactory {

	private final String name;

	/**
	 * Construct a factory to create a File > [name] option that shuts down the application.
	 * @param name the name to use
	 */
	public ExitMenuItemFactory(String name) {
		this.name = name;
	}

	@Override
	public MenuItem makeNode(LocationContext locationContext) {
		MenuItem exit = new MenuItem(name);
		exit.setOnAction(CorrelatingEventHandlerFactory.wrapIfNecessary(
				new ShutdownHookDecorator(new HookExecutingAction(LifecyclePhase.SHUTDOWN))::execute)
		);
		return exit;
	}

}
