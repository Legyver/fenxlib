package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.beans.property.StringProperty;

public interface TextPropertyMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    default T bindToTextProperty(StringProperty textProperty) {
        new ReflectionOperator(builder()).setValue("textProperty", textProperty);
        return builder();
    }

    default StringProperty getTextProperty() {
        return (StringProperty) new ReflectionOperator(builder()).getValue("textProperty");
    }

}
