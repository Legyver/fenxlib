package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.AbstractSelectFileOrDirectoryFactory;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;

import javafx.scene.control.MenuItem;

/**
 * Decorator for a select file or SelectFileMenuFactory that specifies that the file/directory chooser will open in SAVE mode (as opposed to OPEN)
 */
public class SaveFileDecorator extends AbstractMenuItemFactory implements IMenuItemFactory {

	private final SelectFileMenuFactory factory;
	private final String name;
	private final String message;
	private final ThrowingConsumer<FileOptions> fileSelectionConsumer;

	/**
	 * Construct a SaveFileDecorator that decorates a SelectFileMenuFactory that provides save-file functionality to a file chooser
	 * @param name the name of the menu item
	 * @param message the message to be rendered in the title of the file chooser
	 * @param factory the factory to create the file or directory chooser
	 * @param fileSelectionConsumer any action to be taken then a file or directory is selected
	 */
	public SaveFileDecorator(String name, String message, SelectFileMenuFactory factory, ThrowingConsumer<FileOptions> fileSelectionConsumer) {
		this.factory = factory;
		this.name = name;
		this.message = message;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}

	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		return register(locationContext, factory.makeItem(Action.SAVE, name, message, fileSelectionConsumer));
	}

	@Override
	protected String getName() {
		return name;
	}
}
