package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

public interface EditableMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    default T editable(Boolean editable) {
        new ReflectionOperator(builder()).setValue("editable", editable);
        return builder();
    }

    default Boolean isEditable() {
        return (Boolean) new ReflectionOperator(builder()).getValue("editable");
    }

    default BooleanAdapter editableAdapter() {
        return new BooleanAdapter(isEditable());
    }
}
