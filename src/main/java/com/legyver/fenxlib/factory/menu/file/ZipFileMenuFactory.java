package com.legyver.fenxlib.factory.menu.file;

import javafx.stage.FileChooser;

public class ZipFileMenuFactory extends AbstractFileMenuFactory {

	public ZipFileMenuFactory(WorkingFileConfig workingFileConfig) {
		super(workingFileConfig);
	}

	@Override
	public FileChooser getFileChooser(String title) {
		return fileOptionsChooserFactory.makeFileChooser(title, new FileChooser.ExtensionFilter("ZIP", "*.zip"));
	}

}
