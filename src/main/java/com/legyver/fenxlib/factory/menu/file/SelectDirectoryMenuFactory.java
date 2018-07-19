package com.legyver.fenxlib.factory.menu.file;

import com.legyver.fenxlib.factory.DirectoryOptionsChooserFactory;
import com.legyver.fenxlib.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.uimodel.FileOptions;
import com.legyver.fenxlib.util.GuiUtil;
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
			File selectedDirectory = factory.makeDirectoryChooser().showDialog(GuiUtil.getPrimaryStage());

			if (selectedDirectory != null) {
				directorySelectionConsumer.accept(selectedDirectory);
			}
		});
		return select;
	}

}
