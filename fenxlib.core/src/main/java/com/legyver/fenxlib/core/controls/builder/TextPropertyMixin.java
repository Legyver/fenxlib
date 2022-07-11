package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.beans.property.StringProperty;

/**
 * Mixin to specify the property the value of a control should be bound to.
 * To use this mixin, the builder must have a field of type StringProperty called "textProperty"
 * @param <T> the type of the builder using this mixin.
 */
public interface TextPropertyMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    /**
     * Specify the property the text value should be bound to
     * @param textProperty the text property
     * @return the builder using this mixin
     */
    default T bindToTextProperty(StringProperty textProperty) {
        new ReflectionOperator(builder()).setValue("textProperty", textProperty);
        return builder();
    }

    /**
     * Get the text property
     * @return the string property
     */
    default StringProperty getTextProperty() {
        return (StringProperty) new ReflectionOperator(builder()).getValue("textProperty");
    }

}
