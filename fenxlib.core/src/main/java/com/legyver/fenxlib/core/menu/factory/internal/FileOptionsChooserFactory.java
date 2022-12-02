package com.legyver.fenxlib.core.menu.factory.internal;

import com.legyver.fenxlib.api.files.DefaultFileBrowseLocation;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;

import java.util.List;

/**
 * File or Directory Chooser factory that defaults the location the File or Directory Chooser opens to by default to the location last used
 */
public class FileOptionsChooserFactory extends AbstractChooserFactory {

	private final StringProperty initialFileName;

	/**
	 * Construct a factory for File/Directory choosers
	 * @param initialFileName the initial file name
	 */
	public FileOptionsChooserFactory(StringProperty initialFileName) {
		super();
		this.initialFileName = initialFileName;
	}

	/**
	 * Construct a factory for File/Directory choosers
	 * @param defaultFileBrowseLocation the default filesystem location to open to
	 */
	public FileOptionsChooserFactory(DefaultFileBrowseLocation defaultFileBrowseLocation) {
		super(defaultFileBrowseLocation);
		this.initialFileName = defaultFileBrowseLocation.initialFileName();
	}

	/**
	 * Construct a factory to create a FileChooser based on the initial file name
	 */
	public FileOptionsChooserFactory() {
		super();
		this.initialFileName = defaultFileBrowseLocation.initialFileName();
	}

	/**
	 * Make a file chooser with a specific title and file filters
	 * @param title the title of the chooser window
	 * @param filters any filters for files
	 * @return the file chooser
	 */
	public FileChooser makeFileChooser(String title, List<FileChooser.ExtensionFilter> filters) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);

		bindDefaultBrowseLocation(fileChooser.initialDirectoryProperty());
		if (initialFileName != null) {
			fileChooser.initialFileNameProperty().bind(initialFileName);
		}
		if (filters != null) {
			filters.forEach(f -> fileChooser.getExtensionFilters().add(f));
		}
		return fileChooser;
	}

}
