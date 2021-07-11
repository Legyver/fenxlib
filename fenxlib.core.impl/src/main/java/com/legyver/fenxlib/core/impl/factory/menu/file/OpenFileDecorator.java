package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.AbstractSelectFileOrDirectoryFactory;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import javafx.scene.control.MenuItem;

/**
 * Decorate a SelectFileMenuFactory with the Action.OPEN (as opposed to SAVE) for directory and file choosers
 */
public class OpenFileDecorator extends AbstractMenuItemFactory implements IMenuItemFactory {

	private final SelectFileMenuFactory factory;
	private final String name;
	private final String message;
	private final ThrowingConsumer<FileOptions> fileSelectionConsumer;

	/**
	 * Decorate a SelectFileMenuFactory with an open action
	 * @param name the name of the menu item
	 * @param message the message to display on the file or directory chooser title bar
	 * @param factory the factory constructing the file or directory chooser
	 * @param fileSelectionConsumer any action to be taken on the opened file
	 */
	public OpenFileDecorator(String name, String message, SelectFileMenuFactory factory, ThrowingConsumer<FileOptions> fileSelectionConsumer) {
		this.factory = factory;
		this.name = name;
		this.message = message;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}

	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		return register(locationContext, factory.makeItem(Action.OPEN, name, message, fileSelectionConsumer));
	}

	@Override
	protected String getName() {
		return name;
	}
}
