package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.fenxlib.core.scene.controls.factory.TextFieldFactory;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

/**
 * Factory to produce a MFXTextField
 */
public class MFXTextFieldFactory extends TextFieldFactory {

    @Override
    protected TextField makeTextField() {
        return new MFXTextField();
    }
}
