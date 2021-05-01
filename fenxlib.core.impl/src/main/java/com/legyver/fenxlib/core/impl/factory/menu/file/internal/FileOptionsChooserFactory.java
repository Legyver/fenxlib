package com.legyver.fenxlib.core.impl.factory.menu.file.internal;

import com.legyver.core.exception.CoreException;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;

import java.util.List;

public class FileOptionsChooserFactory extends AbstractChooserFactory {

	private final StringProperty initialFileName;

	public FileOptionsChooserFactory(StringProperty initialFileName) throws CoreException {
		super();
		this.initialFileName = initialFileName;
	}

	public FileOptionsChooserFactory(DefaultFileBrowseLocation defaultFileBrowseLocation) {
		super(defaultFileBrowseLocation);
		this.initialFileName = defaultFileBrowseLocation.initialFileName();
	}

	public FileOptionsChooserFactory() throws CoreException {
		super();
		this.initialFileName = defaultFileBrowseLocation.initialFileName();
	}

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
