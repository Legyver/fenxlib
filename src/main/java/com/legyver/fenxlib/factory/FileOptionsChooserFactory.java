package com.legyver.fenxlib.factory;

import java.io.File;
import javafx.stage.FileChooser;

public class FileOptionsChooserFactory {

	private final String title;
	private final String initialDirectory;

	public FileOptionsChooserFactory(String title, String initialDirectory) {
		this.title = title;
		this.initialDirectory = initialDirectory;
	}

	public FileOptionsChooserFactory(String title) {
		this(title, null);
	}

	public FileChooser makeZipFileChooser() {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		if (initialDirectory != null) {
			fileChooser.setInitialDirectory(new File(initialDirectory));
		}
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ZIP", "*.zip"));
		return fileChooser;
	}
}
