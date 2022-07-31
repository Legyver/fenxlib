package com.legyver.fenxlib.core.menu.options;

import com.legyver.fenxlib.api.context.ResourceScope;
import com.legyver.fenxlib.api.layout.PopupDimensions;
import com.legyver.fenxlib.api.layout.PopupLayout;
import com.legyver.fenxlib.api.layout.anchor.CenterContentAnchor;
import com.legyver.fenxlib.api.layout.anchor.PopupAnchor;
import com.legyver.fenxlib.core.controls.factory.SceneFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Option for a Menu Item that launches content in a popup.
 */
public class ShowPopupMenuOption extends AbstractMenuItemOption {

    /**
     * Construct a menu item to launch a popup in a specified position and modality.
     *
     * @param text the text to display on the menu option.
     * @param contentToShow the content to show in a popup
     * @param modality the modality of the window
     * @param popupAnchor supplier of x,y offsets with respect to application window x,y position
     * @param popupDimensions the popupDimensions region to display the popup
     */
    public ShowPopupMenuOption(String text, Parent contentToShow, Modality modality, PopupAnchor popupAnchor, PopupDimensions popupDimensions) {
        super(text, event -> {
            PopupLayout popupLayout = new PopupLayout(contentToShow, text, popupDimensions);
            Stage stage = new Stage();
            SceneFactory sceneFactory = new SceneFactory(stage, null, ResourceScope.POPUPS);
            Scene scene = sceneFactory.makeScene(popupLayout);

            stage.setScene(scene);
            stage.initModality(modality);

            popupAnchor.position(stage, null, popupDimensions);
            stage.showAndWait();
        });
    }

    /**
     * Construct an option to show a popup over the center of the application
     * @param text the text for the menu options
     * @param contentToShow the content to show in the popup
     * @param modality the modality of the popup
     * @param popupDimensions dimensions of the popup
     */
    public ShowPopupMenuOption(String text, Parent contentToShow, Modality modality, PopupDimensions popupDimensions) {
        this(text, contentToShow, modality, new CenterContentAnchor(), popupDimensions);
    }

    /**
     * Construct a menu item to launch a popup in a specified position.
     * This will specify the {@link Modality#APPLICATION_MODAL} modality.
     * @param text         the text to display on the menu option
     * @param contentToShow the content to show in a popup
     * @param target the target region to display the popup
     */
    public ShowPopupMenuOption(String text, Parent contentToShow, PopupDimensions target) {
        this(text, contentToShow, Modality.APPLICATION_MODAL, target);
    }
}
