package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.AbstractSelectFileOrDirectoryFactory;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.DirectoryOptionsChooserFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * DirectoryChooser factory
 */
public class SelectDirectoryMenuFactory extends AbstractSelectFileOrDirectoryFactory<DirectoryOptionsChooserFactory> {

	/**
	 * Construct a DirectoryChooser factory
	 * @throws CoreException if there is an error reading the default browser-to location for the chooser
	 */
	public SelectDirectoryMenuFactory() throws CoreException {
		super(new DirectoryOptionsChooserFactory());
	}

	@Override
	public File choose(Action action, String message, DirectoryOptionsChooserFactory chooserFactory, Stage primaryStage) {
		DirectoryChooser directoryChooser = chooserFactory.makeDirectoryChooser(message);
		return action.choose(directoryChooser, primaryStage);
	}
}
