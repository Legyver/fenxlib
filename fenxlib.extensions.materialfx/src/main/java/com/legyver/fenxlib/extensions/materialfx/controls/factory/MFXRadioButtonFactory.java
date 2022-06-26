package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.fenxlib.core.scene.controls.factory.RadioButtonFactory;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.scene.control.RadioButton;

/**
 * Factory to produce a MFXRadioButton
 */
public class MFXRadioButtonFactory extends RadioButtonFactory {

    @Override
    protected RadioButton makeRadioButton() {
        return new MFXRadioButton();
    }
}
