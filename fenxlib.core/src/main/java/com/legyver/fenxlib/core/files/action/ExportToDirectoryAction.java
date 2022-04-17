package com.legyver.fenxlib.core.files.action;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.files.FileOptions;
import com.legyver.fenxlib.core.files.action.internal.FileChooserAction;
import com.legyver.fenxlib.core.menu.factory.internal.DirectoryOptionsChooserFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Action to launch a directory selector file browser.
 */
public class ExportToDirectoryAction extends AbstractFileSelectionAction<DirectoryOptionsChooserFactory> implements EventHandler<ActionEvent> {
    private final String titleMessage;
    private final ThrowingConsumer<FileOptions> fileSaveConsumer;

    /**
     * Construct an action to launch a directory selector file-browser
     * @param titleMessage the message to display in the file browser
     * @param fileSaveConsumer the consumer to pass the selected directory to
     * @throws CoreException if there is an error getting the default browse location for the file browser window
     */
    public ExportToDirectoryAction(String titleMessage, ThrowingConsumer<FileOptions> fileSaveConsumer) throws CoreException {
        super(new DirectoryOptionsChooserFactory());
        this.titleMessage = titleMessage;
        this.fileSaveConsumer = fileSaveConsumer;
    }

    @Override
    public void handle(ActionEvent event) {
        handleEvent(FileChooserAction.SAVE, titleMessage, fileSaveConsumer);
    }

    @Override
    protected File choose(FileChooserAction action, String message, DirectoryOptionsChooserFactory chooserFactory, Stage primaryStage) {
        DirectoryChooser directoryChooser = chooserFactory.makeDirectoryChooser(message);
        return action.choose(directoryChooser, primaryStage);
    }
}
