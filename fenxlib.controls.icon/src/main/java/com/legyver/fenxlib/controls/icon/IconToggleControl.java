package com.legyver.fenxlib.controls.icon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

/**
 * Control with two icons that toggle depending on it's state.
 */
public class IconToggleControl extends IconControl {
    /**
     * The icon to use within the designated library
     */
    private final StringProperty alternateIconName = new SimpleStringProperty();
    /**
     * Property to show alternate icon.
     * Only one icon will be shown at a time
     */
    private final BooleanProperty showAlternate = new SimpleBooleanProperty(false);

    /**
     * Get the alternate icon name
     * @return alternate icon name
     */
    public String getAlternateIconName() {
        return alternateIconName.get();
    }

    /**
     * Get the alternate item name property
     * @return the property
     *
     */
    public StringProperty alternateIconNameProperty() {
        return alternateIconName;
    }

    /**
     * Set the alternate item name property
     * @param alternateIconName the alternate icon name to use
     */
    public void setAlternateIconName(String alternateIconName) {
        this.alternateIconName.set(alternateIconName);
    }

    /**
     * Check if the alternate icon should be shown
     * @return the show alternate flag
     */
    public boolean isShowAlternate() {
        return showAlternate.get();
    }

    /**
     * Property holding the show alternate flag
     * @return the property
     *
     */
    public BooleanProperty showAlternateProperty() {
        return showAlternate;
    }

    /**
     * Set the show alternate flag
     * @param showAlternate true if the alternate should be shown
     */
    public void setShowAlternate(boolean showAlternate) {
        this.showAlternate.set(showAlternate);
    }

    @Override
    public Skin<?> createDefaultSkin() {
        return new IconToggleControlSkin(this);
    }
}
