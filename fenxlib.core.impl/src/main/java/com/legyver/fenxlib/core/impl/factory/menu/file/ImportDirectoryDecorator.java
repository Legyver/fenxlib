package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import javafx.scene.control.MenuItem;

public class ImportDirectoryDecorator extends AbstractMenuItemFactory implements IMenuItemFactory {
	private final String name;
	private final String message;
	private final SelectDirectoryMenuFactory factory;
	private final ThrowingConsumer<FileOptions> directorySelectionConsumer;

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
