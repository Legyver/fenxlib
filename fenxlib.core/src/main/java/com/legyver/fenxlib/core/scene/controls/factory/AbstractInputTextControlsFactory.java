package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextInputControl;

/**
 * base class for creating controls of type TextInputControl
 * @param <T> the type of the control
 */
public abstract class AbstractInputTextControlsFactory<T extends TextInputControl> {

    /**
     * Constructor parameter to bind the TextInputControl to.
     */
    public static final String BIND_TO = "bind_to";
    /**
     * Constructor parameter to set as the default value for the TextInputControl.
     */
    public static final String DEFAULT_TEXT = "defaultText";
    /**
     * Constructor parameter bind the editable control on the TextInputControl to.
     */
    public static final String BIND_TO_EDITABLE = "bind_to_editable";
    /**
     * Constructor parameter to indicate if the TextInputControl is editable
     */
    public static final String IS_EDITABLE = "is_editable";

    private final BooleanProperty isEditable;
    private final Boolean editable;
    private final StringProperty textProperty;
    private final String defaultText;

    /**
     * Construct a Text Input Control factory
     * @param textProperty the text property to bind to (if any)
     * @param defaultText the default text (if any)
     * @param isEditableProperty property to bind editability to
     * @param isEditable flag that determines if a field is editable
     */
    public AbstractInputTextControlsFactory(StringProperty textProperty, String defaultText, BooleanProperty isEditableProperty, Boolean isEditable) {
        this.isEditable = isEditableProperty;
        this.editable = isEditable;
        this.textProperty = textProperty;
        this.defaultText = defaultText;
        if (isEditable != null && this.isEditable != null) {
            this.isEditable.setValue(isEditable);
        }
    }

    /**
     * Make a text input control
     * @param locationContext the location context to register it as
     * @return the created control
     * @throws CoreException if there is a problem producing the control
     */
    public T makeNode(LocationContext locationContext) throws CoreException {
        T textControl = makeInputControl();

        ApplicationContext.getComponentRegistry().register(locationContext, textControl);
        if (isEditable != null) {
            textControl.editableProperty().bindBidirectional(isEditable);
        }
        if (editable != null) {
            textControl.setEditable(editable);
        }

        if (textProperty != null) {
            textControl.textProperty().bindBidirectional(textProperty);
        }

        if (defaultText != null) {
            textControl.setText(defaultText);
        }

        return textControl;
    }

    /**
     * Factory method to create the input control
     * @return the newly-instantiated input control
     */
    abstract protected T makeInputControl();
}
