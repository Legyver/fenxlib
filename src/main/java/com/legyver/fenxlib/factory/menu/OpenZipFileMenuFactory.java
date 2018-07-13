package com.legyver.fenxlib.factory.menu;

import java.io.File;
import java.util.function.Consumer;
import com.legyver.fenxlib.factory.FileOptionsChooserFactory;
import javafx.stage.FileChooser;

public class OpenZipFileMenuFactory extends OpenFileMenuFactory {

	public OpenZipFileMenuFactory(String name, String message, String initialDirectory, Consumer<File> fileSelectionConsumer) {
		super(name, message, initialDirectory, fileSelectionConsumer);
	}

	public OpenZipFileMenuFactory(String name, String message, Consumer<File> fileSelectionConsumer) {
		this(name, message, null, fileSelectionConsumer);
	}


	@Override
	public FileChooser getFileChooser(FileOptionsChooserFactory factory) {
		return factory.makeZipFileChooser();
	}


}
