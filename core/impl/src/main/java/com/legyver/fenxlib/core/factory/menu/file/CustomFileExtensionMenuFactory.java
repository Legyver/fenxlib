package com.legyver.fenxlib.core.factory.menu.file;

import javafx.stage.FileChooser;

public class CustomFileExtensionMenuFactory extends AbstractFileMenuFactory {
	private final FileChooser.ExtensionFilter[] filters;

	public CustomFileExtensionMenuFactory(DefaultFileBrowseLocation defaultFileBrowseLocation, FileChooser.ExtensionFilter... filters) {
		super(defaultFileBrowseLocation);
		this.filters = filters;
	}

	@Override
	protected FileChooser getFileChooser(String message) {
		return fileOptionsChooserFactory.makeFileChooser(message, filters);
	}

}
