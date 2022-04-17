package com.legyver.fenxlib.core.controls.popup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;

/**
 * Popup with buttons
 */
public class ButtonPopup extends Popup {
    private final ObservableList<Button> buttons = FXCollections.observableArrayList();

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ButtonPopupSkin(this);
    }

    /**
     * Get the buttons to be displayed on the popup.
     * @return the buttons to be displayed on the popup.
     */
    public ObservableList<Button> getButtons() {
        return buttons;
    }
}
