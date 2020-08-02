package com.legyver.fenxlib.core.factory.menu.file;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.factory.DirectoryOptionsChooserFactory;
import com.legyver.fenxlib.core.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.uimodel.FileOptions;
import java.io.File;
import java.util.function.Consumer;
import javafx.scene.control.MenuItem;

public class SelectDirectoryMenuFactory implements IMenuItemFactory<FileOptions> {

	private final String name;
	private final String message;
	private final String initialDirectory;
	private final Consumer<File> directorySelectionConsumer;

	public SelectDirectoryMenuFactory(String name, String message, String initialDirectory, Consumer<File> directorySelectionConsumer) {
		this.name = name;
		this.message = message;
		this.initialDirectory = initialDirectory;
		this.directorySelectionConsumer = directorySelectionConsumer;
	}

	@Override
	public MenuItem makeItem() {
		MenuItem select = new MenuItem(name);
		select.setOnAction(av -> {
			DirectoryOptionsChooserFactory factory = new DirectoryOptionsChooserFactory(message, initialDirectory);
			File selectedDirectory = factory.makeDirectoryChooser().showDialog(ApplicationContext.getPrimaryStage());

			if (selectedDirectory != null) {
				directorySelectionConsumer.accept(selectedDirectory);
			}
		});
		return select;
	}

}
