package com.legyver.fenxlib.core.impl.factory;

import java.io.File;
import javafx.stage.DirectoryChooser;

public class DirectoryOptionsChooserFactory {

	private final String title;
	private final String initialDirectory;

	public DirectoryOptionsChooserFactory(String title, String initialDirectory) {
		this.title = title;
		this.initialDirectory = initialDirectory;
	}

	public DirectoryChooser makeDirectoryChooser() {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle(title);
		if (initialDirectory != null) {
			directoryChooser.setInitialDirectory(new File(initialDirectory));
		}
		return directoryChooser;
	}
}
