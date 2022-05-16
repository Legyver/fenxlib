package com.legyver.fenxlib.core.files.action;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.core.files.marshal.FileMarshalServiceRegistry;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * Action to save a file
 */
public class SaveFileAction implements EventHandler<ActionEvent> {
    private static final Logger logger = LogManager.getLogger(SaveFileAction.class);

    private final Supplier objectToSaveSupplier;

    /**
     * Construct an action to save a file
     * @param objectToSaveSupplier the supplier of the content to save
     */
    public SaveFileAction(Supplier objectToSaveSupplier) {
        this.objectToSaveSupplier = objectToSaveSupplier;
    }

    @Override
    public void handle(ActionEvent event) {
        FileOptions fileOptions = ApplicationContext.getFileRegistry().getActive();

        try {
            FileMarshalServiceRegistry.getInstance().marshal(objectToSaveSupplier.get(), fileOptions);
        } catch (CoreException e) {
            logger.error("Error saving file [" + fileOptions.getFileName() + "]", e);
        }
    }
}
