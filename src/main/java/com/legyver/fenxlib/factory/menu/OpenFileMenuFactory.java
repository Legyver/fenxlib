package com.legyver.fenxlib.factory.menu;

import com.legyver.fenxlib.factory.FileOptionsChooserFactory;
import com.legyver.fenxlib.factory.options.FileOptions;
import com.legyver.fenxlib.util.GuiUtil;
import java.io.File;
import java.util.function.Consumer;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public abstract class OpenFileMenuFactory implements IMenuItemFactory<FileOptions> {
	private final String name;
	private final String message;
	private final String initialDirectory;
	private final Consumer<File> fileSelectionConsumer;

	public OpenFileMenuFactory(String name, String message, String initialDirectory, Consumer<File> fileSelectionConsumer) {
		this.name = name;
		this.message = message;
		this.initialDirectory = initialDirectory;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}
	
	@Override
	public MenuItem makeItem() {
		MenuItem open = new MenuItem(name);
		open.setOnAction(av -> {
			File file = getFileChooser(new FileOptionsChooserFactory(message, initialDirectory))
					.showOpenDialog(GuiUtil.getPrimaryStage());

			if (file != null) {
				fileSelectionConsumer.accept(file);
			}
		});
		return open;
	}
	
	public abstract FileChooser getFileChooser(FileOptionsChooserFactory factory);

}
