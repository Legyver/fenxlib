package com.legyver.fenxlib.factory.menu.file;

import java.io.File;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkingFileConfig {

	private final ObjectProperty<File> initialDirectory = new SimpleObjectProperty<>();
	private final StringProperty initialFileName = new SimpleStringProperty();

	public void setInitialDirectory(File initialDirectory) {
		this.initialDirectory.set(initialDirectory);
	}

	public File getInitialDirectory() {
		return initialDirectory.get();
	}

	public ObjectProperty<File> initialDirectory() {
		return initialDirectory;
	}

	public void setInitialFileName(String initialFileName) {
		this.initialFileName.set(initialFileName);
	}

	public String getInitialFileName() {
		return initialFileName.get();
	}

	public StringProperty initialFileName() {
		return initialFileName;
	}

}
