package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.event.correlation.CorrelatingEventHandlerFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;

import java.io.File;
import java.util.function.Consumer;

import javafx.scene.control.MenuItem;

/**
 * Factory to create a menu item to open a recent file
 */
public class OpenRecentFileFactory implements IMenuItemFactory {
	private final FileOptions fileOptions;
	private final Consumer<File> fileOpenConsumer;

	/**
	 * Construct a factory to create a MenuItem that will open a specific file
	 * @param fileOptions the file to open
	 * @param fileOpenConsumer any action that should be taken when the file is opened
	 */
	public OpenRecentFileFactory(FileOptions fileOptions, Consumer<File> fileOpenConsumer) {
		this.fileOptions = fileOptions;
		this.fileOpenConsumer = fileOpenConsumer;
	}

	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		MenuItem open = new MenuItem(fileOptions.getFile().getName());
		open.setOnAction(CorrelatingEventHandlerFactory.wrapIfNecessary(e -> {
			fileOpenConsumer.accept(fileOptions.getFile());
		}));
		return open;
	}

}
