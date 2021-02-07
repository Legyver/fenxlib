package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import com.legyver.fenxlib.core.impl.factory.menu.file.AbstractFileMenuFactory.Action;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;

import java.util.function.Consumer;

import javafx.scene.control.MenuItem;

public class OpenFileDecorator implements IMenuItemFactory<FileOptions> {

	private final AbstractFileMenuFactory factory;
	private final String name;
	private final String message;
	private final Consumer<FileOptions> fileSelectionConsumer;

	public OpenFileDecorator(String name, String message, AbstractFileMenuFactory factory, Consumer<FileOptions> fileSelectionConsumer) {
		this.factory = factory;
		this.name = name;
		this.message = message;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}

	@Override
	public MenuItem makeItem() throws CoreException {
		return factory.makeItem(Action.OPEN, name, message, fileSelectionConsumer);
	}

}