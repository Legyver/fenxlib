package com.legyver.fenxlib.core.impl.factory.menu.file.internal;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.factory.menu.file.Action;
import com.legyver.fenxlib.core.impl.uimodel.DefaultFileOptions;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public abstract class AbstractSelectFileOrDirectoryFactory<T extends AbstractChooserFactory> {
	private static final Logger logger = LogManager.getLogger(AbstractSelectFileOrDirectoryFactory.class);

	private final T chooserFactory;

	public AbstractSelectFileOrDirectoryFactory(T chooserFactory) {
		this.chooserFactory = chooserFactory;
	}

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

	public abstract File choose(Action action, String message, T chooserFactory, Stage primaryStage);


}
