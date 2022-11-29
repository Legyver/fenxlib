package com.legyver.fenxlib.core.menu.templates.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.core.files.action.OpenRecentFileAction;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.menu.IMenuItemFactory;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import javafx.scene.control.MenuItem;

/**
 * Factory to create a menu item to open a recent file
 */
public class OpenRecentFileFactory implements IMenuItemFactory<MenuItem, MenuItemOptions> {
	private final FileOptions fileOptions;

	/**
	 * Construct a factory to create a MenuItem that will open a specific file
	 * @param fileOptions the file to open
	 */
	public OpenRecentFileFactory(FileOptions fileOptions) {
		this.fileOptions = fileOptions;
	}

	@Override
	public MenuItem makeNode(LocationContext locationContext, MenuItemOptions options) throws CoreException {
		MenuItem open = ControlsFactory.make(MenuItem.class, locationContext.decorateWith(options.getName()), new MenuItemOptions()
				.name(fileOptions.getFileName())
				.eventHandler(new OpenRecentFileAction(fileOptions))
		);
		return open;
	}

	@Override
	public MenuItemOptions newOptions() {
		return new MenuItemOptions();
	}
}
