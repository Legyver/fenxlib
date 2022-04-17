package com.legyver.fenxlib.core.controls.popup;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;

/**
 * Skin for a {@link ButtonPopup}
 */
public class ButtonPopupSkin extends SkinBase<ButtonPopup> {

    private SplitPane splitPane;
    private FlowPane buttonFlow;

    /**
     * Construct a skin for a popup
     * @param buttonPopup the popup to skin
     */
    public ButtonPopupSkin(ButtonPopup buttonPopup) {
        super(buttonPopup);
        ObservableList<Button> buttons = buttonPopup.getButtons();
        buttonFlow = new FlowPane(buttons.toArray(new Button[buttons.size()]));
        buttonFlow.setAlignment(Pos.BASELINE_CENTER);

        splitPane = new SplitPane(buttonPopup.getContent(), buttonFlow);
        splitPane.setBorder(Border.EMPTY);
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.setDividerPosition(0, .80);

        getChildren().add(splitPane);
    }
}
