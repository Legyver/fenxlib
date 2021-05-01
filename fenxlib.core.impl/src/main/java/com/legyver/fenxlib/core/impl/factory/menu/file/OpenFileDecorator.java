package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.AbstractSelectFileOrDirectoryFactory;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import javafx.scene.control.MenuItem;

public class OpenFileDecorator extends AbstractMenuItemFactory implements IMenuItemFactory {

	private final SelectFileMenuFactory factory;
	private final String name;
	private final String message;
	private final ThrowingConsumer<FileOptions> fileSelectionConsumer;

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
