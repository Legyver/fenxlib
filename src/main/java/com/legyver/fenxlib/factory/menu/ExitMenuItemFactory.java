package com.legyver.fenxlib.factory.menu;

import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import com.legyver.fenxlib.util.GuiUtil;
import com.legyver.fenxlib.util.hook.LifecycleHook;

public class ExitMenuItemFactory implements IMenuItemFactory {

	private final String name;

	public ExitMenuItemFactory(String name) {
		this.name = name;
	}

	@Override
	public MenuItem makeItem() {
		MenuItem exit = new MenuItem(name);
		exit.setOnAction(av -> {
			GuiUtil.executeHook(LifecycleHook.PRE_SHUTDOWN);
			Platform.exit();
		});
		return exit;
	}

}
