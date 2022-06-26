package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

public interface SelectedMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    default T selected(Boolean selected) {
        new ReflectionOperator(builder()).setValue("selected", selected);
        return builder();
    }

    default Boolean isSelected() {
        return (Boolean) new ReflectionOperator(builder()).getValue("selected");
    }

    default BooleanAdapter selectedAdapter() {
        return new BooleanAdapter(isSelected());
    }

}
