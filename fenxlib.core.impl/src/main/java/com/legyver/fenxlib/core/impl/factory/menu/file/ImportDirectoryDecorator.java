package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import javafx.scene.control.MenuItem;

/**
 * Factory to create a {@link MenuItem} that opens a {@link javafx.stage.DirectoryChooser} for importing from a specific directory
 */
public class ImportDirectoryDecorator extends AbstractMenuItemFactory implements IMenuItemFactory {
	private final String name;
	private final String message;
	private final SelectDirectoryMenuFactory factory;
	private final ThrowingConsumer<FileOptions> directorySelectionConsumer;

	/**
	 * Decorate a SelectDirectoryMenuFactory with an OPEN action
	 * @param name the name of the menu item
	 * @param message the message to display in the browser window
	 * @param factory the factory that creates the directory browser
	 * @param directorySelectionConsumer any action to be taken when a directory is selected
	 */
	public ImportDirectoryDecorator(String name, String message, SelectDirectoryMenuFactory factory, ThrowingConsumer<FileOptions> directorySelectionConsumer) {
		this.name = name;
		this.message = message;
		this.factory = factory;
		this.directorySelectionConsumer = directorySelectionConsumer;
	}

	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		return register(locationContext, factory.makeItem(Action.OPEN, name, message, directorySelectionConsumer));
	}

	@Override
	protected String getName() {
		return name;
	}
}
