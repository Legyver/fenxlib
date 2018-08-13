package com.legyver.fenxlib.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.menu.file.AbstractFileMenuFactory.Action;
import com.legyver.fenxlib.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.uimodel.FileOptions;
import java.io.File;
import java.util.function.Consumer;
import javafx.scene.control.MenuItem;

public class SaveFileDecorator implements IMenuItemFactory<FileOptions> {

	private final AbstractFileMenuFactory factory;
	private final String name;
	private final String message;
	private final Consumer<File> fileSelectionConsumer;

	public SaveFileDecorator(String name, String message, AbstractFileMenuFactory factory, Consumer<File> fileSelectionConsumer) {
		this.factory = factory;
		this.name = name;
		this.message = message;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}

	@Override
	public MenuItem makeItem() throws CoreException {
		return factory.makeItem(Action.SAVE, name, message, fileSelectionConsumer);
	}

}
