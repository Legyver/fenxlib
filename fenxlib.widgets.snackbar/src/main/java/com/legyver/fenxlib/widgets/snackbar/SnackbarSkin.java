package com.legyver.fenxlib.widgets.snackbar;

import com.legyver.fenxlib.api.alert.Level;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Skin for a Snackbar alert
 */
public class SnackbarSkin extends SkinBase<Snackbar> {

    /**
     * The VBox encapsulating the snackbar content
     */
    protected final VBox vBox;
    private final TextFlow body;

    /**
     * Construct a skin for a snackbar alert
     * @param snackbar the alert to skin
     */
    public SnackbarSkin(Snackbar snackbar) {
        super(snackbar);

        vBox = new VBox();
        vBox.getStyleClass().add("legyver-snackbar");

        body = makeBody(snackbar.getMessage(), snackbar.getLevel());
        vBox.getChildren().add(body);
        VBox.setVgrow(body, Priority.ALWAYS);
        getChildren().add(vBox);
    }

    /**
     * Make the body of the alert.
     * Will be styleable by
     * - #legyver-alert-body-info
     * - #legyver-alert-body-text-info
     * - #legyver-alert-body-warning
     * - #legyver-alert-body-text-warning
     * - #legyver-alert-body-error
     * - #legyver-alert-body-text-error
     * @param message the message to display in the body of the alert
     * @param level the level of the alert
     * @return the body
     */
    protected TextFlow makeBody(String message, Level level) {
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        switch (level) {
            case INFO -> {
                text.setId("legyver-alert-body-text-info");
                textFlow.setId("legyver-alert-body-info");
            }
            case WARNING -> {
                text.setId("legyver-alert-body-text-warning");
                textFlow.setId("legyver-alert-body-warning");
            }
            case ERROR -> {
                text.setId("legyver-alert-body-text-error");
                textFlow.setId("legyver-alert-body-error");
            }

        }
        return textFlow;
    }
}
