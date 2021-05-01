package com.legyver.fenxlib.core.impl.factory.menu;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.util.hook.HookExecutingAction;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.util.hook.decorator.execution.ShutdownHookDecorator;
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
	public MenuItem makeItem(LocationContext locationContext) {
		MenuItem exit = new MenuItem(name);
		exit.setOnAction(new ShutdownHookDecorator(new HookExecutingAction(LifecyclePhase.PRE_SHUTDOWN))::execute);
		return exit;
	}

}
