package com.legyver.fenxlib.core.impl.factory.menu;

import com.legyver.fenxlib.core.impl.util.hook.HookExecutingAction;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.util.hook.decorator.execution.ShutdownHookDecorator;
import javafx.scene.control.MenuItem;

public class ExitMenuItemFactory implements IMenuItemFactory {

	private final String name;

	public ExitMenuItemFactory(String name) {
		this.name = name;
	}

	@Override
	public MenuItem makeItem() {
		MenuItem exit = new MenuItem(name);
		exit.setOnAction(new ShutdownHookDecorator(new HookExecutingAction(LifecyclePhase.PRE_SHUTDOWN))::execute);
		return exit;
	}

}
