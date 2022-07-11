package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.beans.property.BooleanProperty;

/**
 * Mixin to specify a boolean property to bind the control's editable state to.
 * To use this mixin, the builder must have a field of type BooleanProperty called "editableProperty"
 * @param <T> the type of the builder using this mixin.
 */
public interface EditablePropertyMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    /**
     * Specify the property to bind the control's editable state to.
     * @param editableProperty the boolean property
     * @return the builder using this mixin
     */
    default T bindToBooleanProperty(BooleanProperty editableProperty) {
        new ReflectionOperator(builder()).setValue("editableProperty", editableProperty);
        return builder();
    }

    /**
     * Get the boolean property that the control's editable state should be bound to.
     * @return this editable property
     */
    default BooleanProperty getEditableProperty() {
        return (BooleanProperty) new ReflectionOperator(builder()).getValue("editableProperty");
    }

}
