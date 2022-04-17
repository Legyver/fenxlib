package com.legyver.fenxlib.core.files.action;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.files.FileOptions;
import com.legyver.fenxlib.core.files.action.internal.FileChooserAction;
import com.legyver.fenxlib.core.menu.factory.internal.FileOptionsChooserFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * Action to launch a save-as browser window and perform saving of the file
 */
public class SaveAsAction extends AbstractFileSelectionAction<FileOptionsChooserFactory> implements EventHandler<ActionEvent> {
    private final String titleMessage;
    private final List<FileChooser.ExtensionFilter> extensionFilters;
    private final ThrowingConsumer<FileOptions> fileSaveConsumer;

    /**
     * Construct an action to perform save-as
     * @param titleMessage the message to display in the save-as file browser window
     * @param extensionFilters any file extension filters
     * @param fileSaveConsumer the file save consumer that actually saves the file
     * @throws CoreException if there is an error locating the default filesystem browse location for the save-as dialog
     */
    public SaveAsAction(String titleMessage, List<FileChooser.ExtensionFilter> extensionFilters, ThrowingConsumer<FileOptions> fileSaveConsumer) throws CoreException {
        super(new FileOptionsChooserFactory());
        this.titleMessage = titleMessage;
        this.extensionFilters = extensionFilters;
        this.fileSaveConsumer = fileSaveConsumer;
    }

    @Override
    protected File choose(FileChooserAction action, String message, FileOptionsChooserFactory chooserFactory, Stage primaryStage) {
        FileChooser fileChooser = chooserFactory.makeFileChooser(message, extensionFilters);
        return action.choose(fileChooser, primaryStage);
    }

    @Override
    public void handle(ActionEvent event) {
        handleEvent(FileChooserAction.OPEN, titleMessage, fileSaveConsumer);
    }
}
