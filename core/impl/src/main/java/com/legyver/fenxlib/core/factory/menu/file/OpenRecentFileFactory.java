package com.legyver.fenxlib.core.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.uimodel.FileOptions;
import java.io.File;
import java.util.function.Consumer;
import javafx.scene.control.MenuItem;

public class OpenRecentFileFactory implements IMenuItemFactory {
	private final FileOptions fileOptions;
	private final Consumer<File> fileOpenConsumer;

	public OpenRecentFileFactory(FileOptions fileOptions, Consumer<File> fileOpenConsumer) {
		this.fileOptions = fileOptions;
		this.fileOpenConsumer = fileOpenConsumer;
	}

	@Override
	public MenuItem makeItem() throws CoreException {
		MenuItem open = new MenuItem(fileOptions.getFile().getName());
		open.setOnAction(e -> {
			fileOpenConsumer.accept(fileOptions.getFile());
		});
		return open;
	}

}