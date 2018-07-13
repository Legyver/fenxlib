package com.legyver.fenxlib.factory.menu;

import com.legyver.fenxlib.factory.FileOptionsChooserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.stage.FileChooser;

public class OpenCustomFileExtensionMenuFactory extends OpenFileMenuFactory {

	public OpenCustomFileExtensionMenuFactory(String name, String message, String initialDirectory, Consumer<File> fileSelectionConsumer, FileChooser.ExtensionFilter...filters) {
		super(name, message, initialDirectory, fileSelectionConsumer);
	}

	@Override
	public FileChooser getFileChooser(FileOptionsChooserFactory factory) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	public static class Builder {
		private String name;
		private String message;
		private String initialDirectory;
		private Consumer<File> fileSelectionConsumer;
		private final List<FileChooser.ExtensionFilter> filters = new ArrayList<>();
		
		public OpenCustomFileExtensionMenuFactory build() {
			FileChooser.ExtensionFilter[] arrayFilters = filters.toArray(new FileChooser.ExtensionFilter[filters.size()]);
			return new OpenCustomFileExtensionMenuFactory(name, message, initialDirectory, fileSelectionConsumer, arrayFilters);
		}
	}

}
