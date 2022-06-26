package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

public interface DisabledMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    default T disabled(Boolean disabled) {
        new ReflectionOperator(builder()).setValue("disabled", disabled);
        return builder();
    }

    default Boolean isDisabled() {
        return (Boolean) new ReflectionOperator(builder()).getValue("disabled");
    }

    default BooleanAdapter disabledAdapter() {
        return new BooleanAdapter(isDisabled());
    }
}
