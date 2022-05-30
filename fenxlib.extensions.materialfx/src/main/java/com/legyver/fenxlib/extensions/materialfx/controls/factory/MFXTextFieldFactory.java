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
    /**
     * Construct a TextFieldFactory that creates a TextField
     *
     * @param textProperty       the text property to bind to (if any)
     * @param defaultText        the default text (if any)
     * @param isEditableProperty property to bind editability to
     * @param isEditable         flag that determines if a field is editable
     */
    public MFXTextFieldFactory(StringProperty textProperty, String defaultText, BooleanProperty isEditableProperty, Boolean isEditable) {
        super(textProperty, defaultText, isEditableProperty, isEditable);
    }

    @Override
    protected TextField makeTextField() {
        return new MFXTextField();
    }
}
