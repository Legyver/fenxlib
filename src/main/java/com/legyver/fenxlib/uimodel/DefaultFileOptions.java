package com.legyver.fenxlib.uimodel;

import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DefaultFileOptions implements FileOptions {
	private final StringProperty filePath = new SimpleStringProperty();
	private final StringProperty fileName = new SimpleStringProperty();
	private final BooleanProperty newFile = new SimpleBooleanProperty();

	public DefaultFileOptions(boolean newFile) {
		this.newFile.set(newFile);
	}

	public DefaultFileOptions() {
		this(true);
	}

	private File file;

	public String getFilePath() {
		return filePath.get();
	}

	public File getFile() {
		return file;
	}

	@Override
	public void setFilePath(String sourceName) {
		this.filePath.setValue(sourceName);
	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public StringProperty filePathProperty() {
		return filePath;
	}

	@Override
	public void setFileName(String name) {
		fileName.set(name);
	}

	public String getFileName() {
		return fileName.get();
	}

	@Override
	public StringProperty fileNameProperty() {
		return fileName;
	}

	@Override
	public void setNewFile(boolean newFile) {
		this.newFile.set(newFile);
	}

	@Override
	public boolean isNewFile() {
		return newFile.get();
	}

	@Override
	public BooleanProperty newFileProperty() {
		return newFile;
	}

}
