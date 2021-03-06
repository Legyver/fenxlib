package com.legyver.fenxlib.core.impl.factory.menu.file.internal;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.factory.menu.file.Action;
import com.legyver.fenxlib.core.impl.uimodel.DefaultFileOptions;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Abstract factory pattern of one of two types: Select file or Select directory
 * @param <T> the type of the chooser factory (file or directory)
 */
public abstract class AbstractSelectFileOrDirectoryFactory<T extends AbstractChooserFactory> {
	private static final Logger logger = LogManager.getLogger(AbstractSelectFileOrDirectoryFactory.class);

	private final T chooserFactory;

	/**
	 * Construct an AbstractSelectFileOrDirectoryFactory.
	 * @param chooserFactory the factory that creates the File or Directory Chooser
	 */
	protected AbstractSelectFileOrDirectoryFactory(T chooserFactory) {
		this.chooserFactory = chooserFactory;
	}

	/**
	 * Make a MenuItem that opens a File or Directory chooser.
	 * @param action the action to take when the MenuItem is clicked
	 * @param name the name of the MenuItem
	 * @param message the message to be rendered on title bar of the File or Directory chooser
	 * @param fileSelectionConsumer any action to be taken when a file/directory is selected
	 * @return the MenuItem
	 */
	public MenuItem makeItem(Action action, String name, String message, ThrowingConsumer<FileOptions> fileSelectionConsumer) {
		MenuItem open = new MenuItem(name);
		open.setOnAction(av -> {
			File file = choose(action, message, chooserFactory, ApplicationContext.getPrimaryStage());
			if (file != null) {
				FileOptions fileOptions = new DefaultFileOptions(file, false);
				if (action == Action.OPEN) {
					ApplicationContext.getFileRegistry().getOpenFiles().add(fileOptions);
				}
				if (fileSelectionConsumer != null) {
					try {
						fileSelectionConsumer.accept(fileOptions);
					} catch (CoreException e) {
						logger.error("Error processing open file event: " + e.getMessage(), e);
					}
				}
			}
		});
		return open;
	}

	/**
	 * Launch a File/Directory chooser as appropriate
	 * @param action the open/save mode of the chooser
	 * @param message the message to display in the chooser title window
	 * @param chooserFactory the factory that creates the chooser
	 * @param stage the stage to show the chooser over
	 * @return the select file or directory
	 */
	public abstract File choose(Action action, String message, T chooserFactory, Stage stage);


}
