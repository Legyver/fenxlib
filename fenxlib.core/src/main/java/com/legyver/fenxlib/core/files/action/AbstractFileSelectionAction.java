package com.legyver.fenxlib.core.files.action;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.Fenxlib;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.files.DefaultFileOptions;
import com.legyver.fenxlib.core.files.FileOptions;
import com.legyver.fenxlib.core.menu.factory.internal.AbstractChooserFactory;
import com.legyver.fenxlib.core.files.action.internal.FileChooserAction;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Action to handle any type of File Open/Save event where a file browser needs to be displayed
 * @param <T> the type of the chooser factory
 */
public abstract class AbstractFileSelectionAction<T extends AbstractChooserFactory> {
    private static final Logger logger = LogManager.getLogger(AbstractFileSelectionAction.class);

    private final T chooserFactory;

    /**
     * Construct a file selection action
     * @param chooserFactory the chooser factory to use
     */
    protected AbstractFileSelectionAction(T chooserFactory) {
        this.chooserFactory = chooserFactory;
    }

    /**
     * Handle the file open/save action
     * @param action the file chooser action
     * @param message the message to display in the file browser
     * @param fileSelectionConsumer the consumer to be notified when the file is selected
     */
    protected void handleEvent(FileChooserAction action, String message, ThrowingConsumer<FileOptions> fileSelectionConsumer) {
        File file = choose(action, message, chooserFactory, Fenxlib.getPrimaryStage());
        if (file != null) {
            FileOptions fileOptions = new DefaultFileOptions(file, false);
            if (action == FileChooserAction.OPEN) {
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
    }

    /**
     * Launch a file browser window for the file open/save action
     * @param action open or save file browser
     * @param message the message to be displayed in the file browser
     * @param chooserFactory the factory to create the file/directory chooser
     * @param stage the stage to open the file browser over
     * @return the selected file/directory
     */
    abstract protected File choose(FileChooserAction action, String message, T chooserFactory, Stage stage);
}
