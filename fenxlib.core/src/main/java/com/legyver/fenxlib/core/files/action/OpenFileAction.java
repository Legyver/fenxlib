package com.legyver.fenxlib.core.files.action;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.core.menu.factory.internal.FileOptionsChooserFactory;
import com.legyver.fenxlib.core.files.action.internal.FileChooserAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * Action to open a file browser where a user can select a file to open
 */
public class OpenFileAction extends AbstractFileSelectionAction<FileOptionsChooserFactory> implements EventHandler<ActionEvent> {

    private final String titleMessage;
    private final List<FileChooser.ExtensionFilter> extensionFilters;
    private final ThrowingConsumer<FileOptions> fileOpenConsumer;

    /**
     * Construct an action to open a file
     * @param titleMessage the message to display in the file browser title bar
     * @param extensionFilters any file extension filters
     * @param fileOpenConsumer the consumer to process the file once selected
     * @throws CoreException if there is a problem locating the default filesystem browse location for the file browser
     */
    public OpenFileAction(String titleMessage, List<FileChooser.ExtensionFilter> extensionFilters, ThrowingConsumer<FileOptions> fileOpenConsumer) throws CoreException {
        super(new FileOptionsChooserFactory());
        this.titleMessage = titleMessage;
        this.extensionFilters = extensionFilters;
        this.fileOpenConsumer = fileOpenConsumer;
    }

    @Override
    protected File choose(FileChooserAction action, String message, FileOptionsChooserFactory chooserFactory, Stage primaryStage) {
        FileChooser fileChooser = chooserFactory.makeFileChooser(message, extensionFilters);
        return action.choose(fileChooser, primaryStage);
    }

    @Override
    public void handle(ActionEvent event) {
        handleEvent(FileChooserAction.OPEN, titleMessage, fileOpenConsumer);
    }
}
