package com.legyver.fenxlib.core.impl.factory.menu.file.internal;

import java.io.File;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The default location a FileChooser opens
 */
public class DefaultFileBrowseLocation {

	private final ObjectProperty<File> initialDirectory = new SimpleObjectProperty<>();
	private final StringProperty initialFileName = new SimpleStringProperty();

	/**
	 * Set the initial directory
	 * @param initialDirectory the initial directory
	 */
	public void setInitialDirectory(File initialDirectory) {
		this.initialDirectory.set(initialDirectory);
	}

	/**
	 * Get the initial directory
	 * @return the initial directory
	 */
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
