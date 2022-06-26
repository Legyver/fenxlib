package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.beans.property.BooleanProperty;

public interface EditablePropertyMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    default T bindToBooleanProperty(BooleanProperty editableProperty) {
        new ReflectionOperator(builder()).setValue("editableProperty", editableProperty);
        return builder();
    }

    default BooleanProperty getEditableProperty() {
        return (BooleanProperty) new ReflectionOperator(builder()).getValue("editableProperty");
    }

}
