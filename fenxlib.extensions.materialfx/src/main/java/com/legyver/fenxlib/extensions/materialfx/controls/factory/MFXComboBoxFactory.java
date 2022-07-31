package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXComboBoxOptions;
import io.github.palexdev.materialfx.controls.MFXComboBox;

/**
 * Factory to produce a MFXComboBox
 */
public class MFXComboBoxFactory implements NodeFactory<MFXComboBox, MFXComboBoxOptions> {

    @Override
    public MFXComboBox makeNode(LocationContext locationContext, MFXComboBoxOptions options) throws CoreException {
        MFXComboBox comboBox = makeComboBox();
        options.itemsAdapter().setNotNull(list -> comboBox.setItems(list));
        Fenxlib.register(locationContext.decorateWith(options.getName()), comboBox);
        return comboBox;
    }

    @Override
    public MFXComboBoxOptions newOptions() {
        return new MFXComboBoxOptions();
    }

    /**
     * factory method to produce a MFXComboBox
     *
     * @return a new Combo Box
     */
    protected MFXComboBox makeComboBox() {
        return new MFXComboBox();
    }
}
