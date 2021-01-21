package com.legyver.fenxlib.core.impl.factory.menu.file;

import javafx.stage.FileChooser;

public class ZipFileMenuFactory extends AbstractFileMenuFactory {

	public ZipFileMenuFactory(DefaultFileBrowseLocation defaultFileBrowseLocation) {
		super(defaultFileBrowseLocation);
	}

	@Override
	public FileChooser getFileChooser(String title) {
		return fileOptionsChooserFactory.makeFileChooser(title, new FileChooser.ExtensionFilter("ZIP", "*.zip"));
	}

}
