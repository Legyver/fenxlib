package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.fenxlib.core.scene.controls.factory.CheckBoxFactory;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.scene.control.CheckBox;

/**
 * Factory to create a MaterialFX Checkbox
 */
public class MFXCheckBoxFactory extends CheckBoxFactory {

    @Override
    protected CheckBox makeCheckBox() {
        return new MFXCheckbox();
    }
}
