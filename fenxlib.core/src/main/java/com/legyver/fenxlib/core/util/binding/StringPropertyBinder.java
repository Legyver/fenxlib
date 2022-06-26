package com.legyver.fenxlib.core.util.binding;

import javafx.beans.property.StringProperty;
import javafx.css.Styleable;
import javafx.scene.text.Text;

/**
 * Binder to applying binding between a control's {@link Text#textProperty()} and a StringProperty
 *
 */
public class StringPropertyBinder {
    private final StringProperty stringProperty;
    private final BindType bindType;

    /**
     * Construct a binder to applying binding between a control's {@link Text#textProperty()} and a StringProperty
     * @param stringProperty the string property to use in the binding
     * @param bindType the type of the binding
     */
    public StringPropertyBinder(StringProperty stringProperty, BindType bindType) {
        this.stringProperty = stringProperty;
        this.bindType = bindType;
    }

    /**
     * Construct a binder to bind a control's {@link Text#textProperty()} to an external string property
     * @param stringProperty the property to bind to
     */
    public StringPropertyBinder(StringProperty stringProperty) {
        this(stringProperty, BindType.CONTROL_BOUND_TO_PROPERTY);
    }

    /**
     * Bind a property with a control's {@link Text#textProperty()}
     * @param control the control to bind
     * @return the control {@link Text#textProperty()}
     */
    public StringProperty bind(Styleable control) {
        StringProperty controlProperty = null;
        if (control instanceof Text) {
            controlProperty = ((Text) control).textProperty();
        }
        if (controlProperty != null && stringProperty != null) {
            switch (bindType) {
                case BIDIRECTIONAL -> controlProperty.bindBidirectional(stringProperty);
                case CONTROL_BOUND_TO_PROPERTY -> controlProperty.bind(stringProperty);
                case PROPERTY_BOUND_TO_CONTROL -> stringProperty.bind(controlProperty);
                default -> {}//noop
            }
        }
        return controlProperty;
    }
}
