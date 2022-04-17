package com.legyver.fenxlib.core.files.action;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.files.FileOptions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Action to open a recent file
 */
public class OpenRecentFileAction implements EventHandler<ActionEvent> {
    private final FileOptions fileOptions;

    /**
     * Construct an action to open a recent file
     * @param fileOptions the recent file to open
     */
    public OpenRecentFileAction(FileOptions fileOptions) {
        this.fileOptions = fileOptions;
    }

    @Override
    public void handle(ActionEvent event) {
        ApplicationContext.getFileRegistry().getOpenFiles().add(fileOptions);
    }
}
