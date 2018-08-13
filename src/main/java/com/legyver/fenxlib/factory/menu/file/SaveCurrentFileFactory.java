package com.legyver.fenxlib.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.uimodel.FileOptions;
import java.io.File;
import java.util.function.Consumer;
import javafx.scene.control.MenuItem;

public class SaveCurrentFileFactory implements IMenuItemFactory {
	private final String label;
	private final FileOptions currentFile;
	private final Consumer<File> fileSaveConsumer;

	public SaveCurrentFileFactory(String label, FileOptions currentFile, Consumer<File> fileSaveConsumer) {
		this.label = label;
		this.currentFile = currentFile;
		this.fileSaveConsumer = fileSaveConsumer;
	}

	@Override
	public MenuItem makeItem() throws CoreException {
		MenuItem save = new MenuItem(label);
		save.disableProperty().bind(currentFile.newFileProperty());
		save.setOnAction(av -> {
			fileSaveConsumer.accept(currentFile.getFile());
		});
		return save;
	}

}
