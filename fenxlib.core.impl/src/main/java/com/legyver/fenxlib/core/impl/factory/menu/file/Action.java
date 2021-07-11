package com.legyver.fenxlib.core.impl.factory.menu.file;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * The type of action the file/directory chooser will be doing.
 */
public enum Action {
	/**
	 * Opens the file/directory browser in open/import-from file/directory mode
	 */
	OPEN {
		@Override
		public File choose(FileChooser fileChooser, Stage stage) {
			return fileChooser.showOpenDialog(stage);
		}
	},
	/**
	 * Opens the file/directory browser in save/export-to file/directory mode
	 */
	SAVE {
		@Override
		public File choose(FileChooser fileChooser, Stage stage) {
			return fileChooser.showSaveDialog(stage);
		}
	};

	/**
	 * Display the choose directory dialog
	 * @param directoryChooser the {@link DirectoryChooser} to use
	 * @param stage the stage to display the DirectoryChooser over
	 * @return the chosen directory
	 */
	public File choose(DirectoryChooser directoryChooser, Stage stage) {
		return directoryChooser.showDialog(stage);
	}

	/**
	 * Display the choose file dialog
	 * @param fileChooser the {@link FileChooser} to use
	 * @param stage the stage to display the FileChooser over
	 * @return the chosen file
	 */
	abstract public File choose(FileChooser fileChooser, Stage stage);
}
