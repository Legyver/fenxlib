package com.legyver.fenxlib.core.controls.popup;

import javafx.scene.control.SkinBase;

/**
 * The Popup skin
 */
public class PopupSkin extends SkinBase<Popup> {
    /**
     * Construct a popup skin
     * @param popup the popup to skin
     */
    public PopupSkin(Popup popup) {
        super(popup);

        getChildren().add(popup.getContent());
    }
}
