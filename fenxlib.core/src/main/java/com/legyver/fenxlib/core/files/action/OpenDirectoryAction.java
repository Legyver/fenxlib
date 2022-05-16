package com.legyver.fenxlib.core.files.action;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.core.menu.factory.internal.DirectoryOptionsChooserFactory;
import com.legyver.fenxlib.core.files.action.internal.FileChooserAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Action to launch a file browser where the user can select a directory to import
 */
public class OpenDirectoryAction extends AbstractFileSelectionAction<DirectoryOptionsChooserFactory> implements EventHandler<ActionEvent> {
    private final String titleMessage;
    private final ThrowingConsumer<FileOptions> fileOpenConsumer;

    /**
     * Construct an action to open a directory
     * @param titleMessage the message to display in the file browser title bar
     * @param fileOpenConsumer the consumer to process the directory when selected
     * @throws CoreException if there is an error locating the default file system location to browse to
     */
    public OpenDirectoryAction(String titleMessage, ThrowingConsumer<FileOptions> fileOpenConsumer) throws CoreException {
        super(new DirectoryOptionsChooserFactory());
        this.titleMessage = titleMessage;
        this.fileOpenConsumer = fileOpenConsumer;
    }

    @Override
    protected File choose(FileChooserAction action, String message, DirectoryOptionsChooserFactory chooserFactory, Stage primaryStage) {
        DirectoryChooser directoryChooser = chooserFactory.makeDirectoryChooser(message);
        return action.choose(directoryChooser, primaryStage);
    }

    @Override
    public void handle(ActionEvent event) {
        handleEvent(FileChooserAction.OPEN, titleMessage, fileOpenConsumer);
    }
}
