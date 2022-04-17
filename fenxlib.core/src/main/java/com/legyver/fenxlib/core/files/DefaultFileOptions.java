package com.legyver.fenxlib.core.files;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Default implementation of FileOptions
 */
public class DefaultFileOptions implements FileOptions {
	/**
	 * The path of the file
	 */
	private final StringProperty filePath = new SimpleStringProperty();
	/**
	 * The (simple) name of the file
	 */
	private final StringProperty fileName = new SimpleStringProperty();
	/**
	 * Flag to tell if the file is to be created (true) or already exists (false)
	 */
	private final BooleanProperty newFile = new SimpleBooleanProperty();

	/**
	 * The actual file
	 */
	private final File file;

	/**
	 * The metadata associated with the file
	 */
	private final Map<String, Object> metadata = new HashMap<>();

	/**
	 * Construct file options for the file
	 * @param file the file
	 * @param newFile flag to set if the file is a new file
	 */
	public DefaultFileOptions(File file, boolean newFile) {
		this.file = file;
		this.newFile.set(newFile);
		if (file != null) {
			fileName.set(file.getName());
			filePath.set(file.getAbsolutePath());
		}
	}

	/**
	 * Construct file options for the file
	 * - the file reference is set to null
	 * - the new file flag is set to true
	 */
	public DefaultFileOptions() {
		this(null, true);
	}

	@Override
	public void setFilePath(String sourceName) {
		this.filePath.setValue(sourceName);
	}

	@Override
	public String getFilePath() {
		return filePath.get();
	}

	@Override
	public StringProperty filePathProperty() {
		return filePath;
	}

	@Override
	public Map<String, Object> getMetadata() {
		return metadata;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void setFileName(String name) {
		fileName.set(name);
	}

	@Override
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DefaultFileOptions that = (DefaultFileOptions) o;

		return filePath != null ? filePath.equals(that.filePath) : that.filePath == null;
	}

	@Override
	public int hashCode() {
		return filePath != null ? filePath.hashCode() : 0;
	}

	@Override
	public int compareTo(FileOptions o) {
		//sort by name
		return this.fileName.get().compareTo(o.getFileName());
	}
}
