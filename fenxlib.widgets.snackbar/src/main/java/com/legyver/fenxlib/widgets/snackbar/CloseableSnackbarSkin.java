package com.legyver.fenxlib.widgets.snackbar;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.scene.controls.options.ButtonOptions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Skin for a closeable snackbar which adds a close button
 */
public class CloseableSnackbarSkin extends SnackbarSkin {
    private static final Logger logger = LogManager.getLogger(CloseableSnackbarSkin.class);

    /**
     * Construct a skin for a snackbar that allows users to manually dismiss it
     * @param closeableSnackbar the snackbar to skin
     */
    public CloseableSnackbarSkin(CloseableSnackbar closeableSnackbar) {
        super(closeableSnackbar);
        try {
            Button button = ControlsFactory.make(Button.class, new ButtonOptions().text("x"));
            button.setId("legyver-alert-close-btn");
            //        button.setBlendMode(BlendMode.OVERLAY);
//            button.setPrefSize(10, 10);
//            button.setMaxHeight(25);
            button.setOnAction(new EventHandler<>() {
                @Override
                public void handle(ActionEvent event) {
                    closeableSnackbar.setClose(true);
                }
            });
            button.setTextAlignment(TextAlignment.CENTER);

            Region spacer = new Region();
            HBox hBox = new HBox(spacer, button);
//            hBox.setMaxHeight(25);
            HBox.setHgrow(spacer, Priority.ALWAYS);
            HBox.setHgrow(button, Priority.SOMETIMES);
            hBox.setAlignment(Pos.CENTER_RIGHT);
            vBox.getChildren().add(0, hBox);
            VBox.setVgrow(hBox, Priority.SOMETIMES);
        } catch (CoreException e) {
            logger.error(e);
        }
    }
}
