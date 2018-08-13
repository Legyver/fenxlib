package com.legyver.fenxlib.factory.menu.file;

import com.legyver.fenxlib.factory.menu.file.WorkingFileConfig;
import java.io.File;
import java.util.stream.Stream;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;

public class FileOptionsChooserFactory {

	private final ObjectProperty<File> initialDirectory;
	private final StringProperty initialFileName;

	public FileOptionsChooserFactory(ObjectProperty<File> initialDirectory, StringProperty initialFileName) {
		this.initialDirectory = initialDirectory;
		this.initialFileName = initialFileName;
	}

	public FileOptionsChooserFactory(WorkingFileConfig workingFileConfig) {
		this(workingFileConfig != null ? workingFileConfig.initialDirectory() : null, workingFileConfig != null ? workingFileConfig.initialFileName() : null);
	}

	public FileChooser makeFileChooser(String title, FileChooser.ExtensionFilter... filters) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);

		if (initialDirectory != null) {
			fileChooser.initialDirectoryProperty().bind(initialDirectory);
		}
		if (initialFileName != null) {
			fileChooser.initialFileNameProperty().bind(initialFileName);
		}
		if (filters != null) {
			Stream.of(filters).forEach(f -> fileChooser.getExtensionFilters().add(f));
		}
		return fileChooser;
	}

}
