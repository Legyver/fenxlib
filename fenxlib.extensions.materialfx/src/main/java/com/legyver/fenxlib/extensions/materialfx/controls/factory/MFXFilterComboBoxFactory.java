package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

/**
 * Factory to produce a MFXFilterComboBox
 */
public class MFXFilterComboBoxFactory extends MFXComboBoxFactory {

    /**
     * Factory method to make a MFXFilterComboFox
     * @return a new MFXFilterComboBox
     */
    protected MFXComboBox makeComboBox() {
        return new MFXFilterComboBox();
    }
}
