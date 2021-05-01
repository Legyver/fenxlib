package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.AbstractSelectFileOrDirectoryFactory;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.DirectoryOptionsChooserFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class SelectDirectoryMenuFactory extends AbstractSelectFileOrDirectoryFactory<DirectoryOptionsChooserFactory> {

	public SelectDirectoryMenuFactory() throws CoreException {
		super(new DirectoryOptionsChooserFactory());
	}

	@Override
	public File choose(Action action, String message, DirectoryOptionsChooserFactory chooserFactory, Stage primaryStage) {
		DirectoryChooser directoryChooser = chooserFactory.makeDirectoryChooser(message);
		return action.choose(directoryChooser, primaryStage);
	}
}
