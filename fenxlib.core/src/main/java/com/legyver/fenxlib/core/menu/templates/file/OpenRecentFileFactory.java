package com.legyver.fenxlib.core.menu.templates.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.ControlsFactory;
import com.legyver.fenxlib.core.event.correlation.CorrelatingEventHandlerFactory;
import com.legyver.fenxlib.core.files.FileOptions;
import com.legyver.fenxlib.core.files.action.OpenFileAction;
import com.legyver.fenxlib.core.files.action.OpenRecentFileAction;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.menu.factory.IMenuItemFactory;
import com.legyver.fenxlib.core.menu.factory.MenuItemFactory;
import com.legyver.fenxlib.core.util.MapBuilder;
import javafx.css.Styleable;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.util.function.Consumer;

/**
 * Factory to create a menu item to open a recent file
 */
public class OpenRecentFileFactory implements IMenuItemFactory<MenuItem> {
	private final FileOptions fileOptions;

	/**
	 * Construct a factory to create a MenuItem that will open a specific file
	 * @param fileOptions the file to open
	 */
	public OpenRecentFileFactory(FileOptions fileOptions) {
		this.fileOptions = fileOptions;
	}

	@Override
	public MenuItem makeNode(LocationContext locationContext) throws CoreException {
		MenuItem open = ControlsFactory.make(MenuItem.class, locationContext, MapBuilder
				.of(MenuItemFactory.PARAM_NAME, fileOptions.getFileName())
				.with(MenuItemFactory.PARAM_ACTION, new OpenRecentFileAction(fileOptions))
				.build());
		return open;
	}
}
