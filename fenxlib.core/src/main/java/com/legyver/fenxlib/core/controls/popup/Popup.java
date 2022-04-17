package com.legyver.fenxlib.core.controls.popup;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Popup widget
 */
public class Popup extends Control {
    private Node content;

    @Override
    protected Skin<?> createDefaultSkin() {
        return new PopupSkin(this);
    }

    /**
     * Get the content of the popup
     * @return the content
     */
    public Node getContent() {
        return content;
    }

    /**
     * Set the content of the popup
     * @param content the content to display in the popup
     */
    public void setContent(Node content) {
        this.content = content;
    }
}
