package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

/**
 * Factory to create a TextArea control
 */
public class TextAreaFactory<T extends TextArea> extends AbstractInputTextControlsFactory<T> implements NodeFactory<T> {

    /**
     * Construct a factory to produce a Text Area
     * @param textProperty the text property to bind to (if any)
     * @param defaultText the default text (if any)
     * @param isEditableProperty property to bind editability to
     * @param isEditable flag that determines if a field is editable
     */
    public TextAreaFactory(StringProperty textProperty, String defaultText, BooleanProperty isEditableProperty, Boolean isEditable) {
        super(textProperty, defaultText, isEditableProperty, isEditable);
    }

    @Override
    protected T makeInputControl() {
        return (T) makeTextArea();
    }

    /**
     * Factory method to instantiate a TextArea.
     * @return a javafx TextArea by default, override if you need something else
     */
    protected TextArea makeTextArea() {
        return new TextArea();
    }
}
