package com.legyver.fenxlib.factory.menu;

import java.io.File;
import java.util.function.Consumer;
import javafx.scene.control.MenuItem;
import com.legyver.fenxlib.factory.FileOptionsChooserFactory;
import com.legyver.fenxlib.factory.options.FileOptions;
import com.legyver.fenxlib.util.GuiUtil;

public class OpenZipFileMenuFactory implements IMenuItemFactory<FileOptions> {

	private final String name;
	private final String message;
	private final String initialDirectory;
	private final Consumer<File> fileSelectionConsumer;

	public OpenZipFileMenuFactory(String name, String message, String initialDirectory, Consumer<File> fileSelectionConsumer) {
		this.name = name;
		this.message = message;
		this.initialDirectory = initialDirectory;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}

	public OpenZipFileMenuFactory(String name, String message, Consumer<File> fileSelectionConsumer) {
		this(name, message, null, fileSelectionConsumer);
	}

	@Override
	public MenuItem makeItem() {
		MenuItem open = new MenuItem(name);
		open.setOnAction(av -> {
			FileOptionsChooserFactory factory = new FileOptionsChooserFactory(message, initialDirectory);
			File zipFile = factory.makeZipFileChooser().showOpenDialog(GuiUtil.getPrimaryStage());

			if (zipFile != null) {
				fileSelectionConsumer.accept(zipFile);
			}
		});
		return open;
	}


}
