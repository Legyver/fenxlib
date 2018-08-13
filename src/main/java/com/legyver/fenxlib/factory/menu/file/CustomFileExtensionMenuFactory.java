package com.legyver.fenxlib.factory.menu.file;

import javafx.stage.FileChooser;

public class CustomFileExtensionMenuFactory extends AbstractFileMenuFactory {
	private final FileChooser.ExtensionFilter[] filters;

	public CustomFileExtensionMenuFactory(WorkingFileConfig workingFileConfig, FileChooser.ExtensionFilter... filters) {
		super(workingFileConfig);
		this.filters = filters;
	}

	@Override
	protected FileChooser getFileChooser(String message) {
		return fileOptionsChooserFactory.makeFileChooser(message, filters);
	}

}
