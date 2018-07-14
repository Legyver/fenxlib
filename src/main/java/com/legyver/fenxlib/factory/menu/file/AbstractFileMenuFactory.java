package com.legyver.fenxlib.factory.menu.file;

import com.legyver.fenxlib.factory.FileOptionsChooserFactory;
import com.legyver.fenxlib.util.GuiUtil;
import java.io.File;
import java.util.function.Consumer;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Defines FileChooser initial directory, an initial file name (if any), what
 * files types to show, etc
 */
public abstract class AbstractFileMenuFactory {

	protected final FileOptionsChooserFactory fileOptionsChooserFactory;

	public AbstractFileMenuFactory(WorkingFileConfig workingFileConfig) {
		fileOptionsChooserFactory = new FileOptionsChooserFactory(workingFileConfig);
	}

	/**
	 * @param action: Opening/Saving
	 * @param name: Name of the menu item
	 * @param message: Message displayed on FileChooser
	 * @param fileSelectionConsumer: Consumer that processes the selected file
	 */
	public MenuItem makeItem(Action action, String name, String message, Consumer<File> fileSelectionConsumer) {
		MenuItem open = new MenuItem(name);
		open.setOnAction(av -> {
			File file = action.choose(getFileChooser(message), GuiUtil.getPrimaryStage());
			if (file != null) {
				if (fileSelectionConsumer != null) {
					fileSelectionConsumer.accept(file);
				}
			}
		});
		return open;
	}

	protected abstract FileChooser getFileChooser(String message);

	public enum Action {
		OPEN {
			@Override
			File choose(FileChooser fileChooser, Stage stage) {
				return fileChooser.showOpenDialog(stage);
			}
		}, SAVE {
			@Override
			File choose(FileChooser fileChooser, Stage stage) {
				return fileChooser.showSaveDialog(stage);
			}
		};

		abstract File choose(FileChooser fileChooser, Stage stage);
	}

}
