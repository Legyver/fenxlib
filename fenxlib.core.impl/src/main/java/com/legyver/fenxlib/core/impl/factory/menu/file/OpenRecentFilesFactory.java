package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Factory to create a menu item to open a recent file
 */
public class OpenRecentFilesFactory extends AbstractMenuItemFactory implements IMenuItemFactory {
	/**
	 * Default name for the recent file menu
	 */
	public static final String NAME = "Recent";
	private final List<OpenRecentFileFactory> factories;
	private final boolean noRecentFiles;

	/**
	 * Construct a factory to create a menu item to open a recent file
	 * @param recentFiles list of recent files to create menu items for
	 * @param fileOpenConsumer any action that should be taken when a file is opened
	 */
	public OpenRecentFilesFactory(List<FileOptions> recentFiles, Consumer<File> fileOpenConsumer) {
		this.noRecentFiles = recentFiles.isEmpty();
		factories = recentFiles.stream().map(option -> new OpenRecentFileFactory(option, fileOpenConsumer)).collect(Collectors.toList());
	}

	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		Menu recentMenu = new Menu(NAME);
		recentMenu.setDisable(noRecentFiles);
		for (OpenRecentFileFactory factory : factories) {
			recentMenu.getItems().add(factory.makeItem(locationContext));
		}
		return register(locationContext, recentMenu);
	}

	@Override
	protected String getName() {
		return NAME;
	}
}
