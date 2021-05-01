package com.legyver.fenxlib.core.impl.factory.menu.file;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * The type of action the file chooser will be doing.
 */
public enum Action {
	OPEN {
		@Override
		public File choose(FileChooser fileChooser, Stage stage) {
			return fileChooser.showOpenDialog(stage);
		}
	}, SAVE {
		@Override
		public File choose(FileChooser fileChooser, Stage stage) {
			return fileChooser.showSaveDialog(stage);
		}
	};

	public File choose(DirectoryChooser fileChooser, Stage stage) {
		return fileChooser.showDialog(stage);
	}

	abstract public File choose(FileChooser fileChooser, Stage stage);
}
