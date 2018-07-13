package com.legyver.fenxlib.factory;

import java.io.File;
import java.util.stream.Stream;
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
	
	public FileChooser makeFileChooser(FileChooser.ExtensionFilter...filters) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		if (initialDirectory != null) {
			fileChooser.setInitialDirectory(new File(initialDirectory));
		}
		if (filters != null) {
			Stream.of(filters).forEach(f -> fileChooser.getExtensionFilters().add(f));
		}
		return fileChooser;
	}

	public FileChooser makeZipFileChooser() {
		return makeFileChooser(new FileChooser.ExtensionFilter("ZIP", "*.zip"));
	}
	
	
}
