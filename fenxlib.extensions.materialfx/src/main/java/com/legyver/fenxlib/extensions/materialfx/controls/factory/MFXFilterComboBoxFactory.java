package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

import java.util.List;

/**
 * Factory to produce a MFXFilterComboBox
 */
public class MFXFilterComboBoxFactory extends MFXComboBoxFactory {
    /**
     * Construct a combo box with the requisite items
     *
     * @param items the items for the combo box.
     *              If no items are specified, the choice box will be empty.
     *              If the item list is an observable list, the items are passed to the combo box without further ado
     *              otherwise an observable list is constructed of the items, and that is passed to the combo box
     */
    public MFXFilterComboBoxFactory(List items) {
        super(items);
    }

    /**
     * Factory method to make a MFXFilterComboFox
     * @return a new MFXFilterComboBox
     */
    protected MFXComboBox makeComboBox() {
        return new MFXFilterComboBox();
    }
}
