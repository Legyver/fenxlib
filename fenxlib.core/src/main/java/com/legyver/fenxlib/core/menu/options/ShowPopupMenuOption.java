package com.legyver.fenxlib.core.menu.options;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.core.layout.Target;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;

/**
 * Option for a Menu Item that launches content in a popup.
 */
public class ShowPopupMenuOption extends AbstractMenuItemOption {
    /**
     * Construct a menu item to launch a popup in a specified position and modality.
     *
     * @param text         the text to display on the menu option
     * @param contentToShow the content to show in a popup
     * @param modality the modality of the window
     * @param target the target region to display the popup
     */
    public ShowPopupMenuOption(String text, Parent contentToShow, Modality modality, Target target) {
        super(text, event -> {
            Scene scene = new Scene(contentToShow);
            List<URL> stylesheetUrls = ApplicationContext.getStylesheets();
            if (stylesheetUrls != null) {
                stylesheetUrls.stream().map(url -> url.toExternalForm())
                        .forEach(styleSheet -> scene.getStylesheets().add(styleSheet));
            }
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(modality);
            stage.setHeight(target.getPrefHeight());
            stage.setWidth(target.getPrefWidth());

            Stage primaryStage = ApplicationContext.getPrimaryStage();
            double x = primaryStage.getX();
            double width = primaryStage.getWidth();
            double widthDiff = Math.abs(width - target.getPrefWidth());
            double insetX = widthDiff / 2.0;
            stage.setX(x + insetX);

            double y = primaryStage.getY();
            double height = primaryStage.getHeight();
            double heightDiff = Math.abs(height - target.getPrefHeight());
            double insetY = heightDiff / 2.0;
            stage.setY(y + insetY);

            stage.showAndWait();
        });
    }

    /**
     * Construct a menu item to launch a popup in a specified position.
     * This will specify the {@link Modality#APPLICATION_MODAL} modality.
     * @param text         the text to display on the menu option
     * @param contentToShow the content to show in a popup
     * @param target the target region to display the popup
     */
    public ShowPopupMenuOption(String text, Parent contentToShow, Target target) {
        this(text, contentToShow, Modality.APPLICATION_MODAL, target);
    }
}
