package com.legyver.fenxlib.core.impl.factory.menu.file.internal;

import com.legyver.core.exception.CoreException;
import javafx.stage.DirectoryChooser;

/**
 * Factory to produce the DirectoryChooser
 */
public class DirectoryOptionsChooserFactory extends AbstractChooserFactory {

	/**
	 * Construct a directory chooser with the initial directory of the last-opened file as loaded from config
	 * @throws CoreException if an error is encountered reading the last-opened file from the config
	 */
	public DirectoryOptionsChooserFactory() throws CoreException {
		super();
	}

	/**
	 * Create the DirectoryChooser
	 * @param title the title for the directory chooser
	 * @return the DirectoryChooser
	 */
	public DirectoryChooser makeDirectoryChooser(String title) {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle(title);
		bindDefaultBrowseLocation(directoryChooser.initialDirectoryProperty());
		return directoryChooser;
	}
}
