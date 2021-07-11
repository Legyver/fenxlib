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

	/**
	 * Get the initial directory property
	 * @return the initial directory property
	 */
	public ObjectProperty<File> initialDirectory() {
		return initialDirectory;
	}

	/**
	 * Set the initial file name
	 * @param initialFileName the initial file name
	 */
	public void setInitialFileName(String initialFileName) {
		this.initialFileName.set(initialFileName);
	}

	/**
	 * Get the initial file name
	 * @return the initial file name
	 */
	public String getInitialFileName() {
		return initialFileName.get();
	}

	/**
	 * Get the initial file name property
	 * @return the initial file name property
	 */
	public StringProperty initialFileName() {
		return initialFileName;
	}

}
