package com.legyver.fenxlib.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.uimodel.FileOptions;
import java.util.function.Consumer;
import javafx.scene.control.MenuItem;

public class CloseCurrentFileFactory implements IMenuItemFactory<FileOptions> {

	private final String text;
	private final FileOptions workingFile;
	private final Consumer callback;

	public CloseCurrentFileFactory(String text, FileOptions workingFile, Consumer callback) {
		this.text = text;
		this.workingFile = workingFile;
		this.callback = callback;
	}

	@Override
	public MenuItem makeItem() throws CoreException {
		MenuItem menuItem = new MenuItem(text);
		menuItem.setOnAction(e -> {
			workingFile.setFile(null);
			workingFile.setFileName(null);
			workingFile.setFilePath(null);
			workingFile.setNewFile(true);
			callback.accept(null);
		});
		return menuItem;
	}

}
