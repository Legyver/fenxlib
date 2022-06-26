package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.factory.adapters.DoubleAdapter;
import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

public interface DoubleValueMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    default T value(Double value) {
        new ReflectionOperator(builder()).setValue("value", value);
        return builder();
    }

    default Double getValue() {
        return (Double) new ReflectionOperator(builder()).getValue("value");
    }

    default DoubleAdapter valueAdapter() {
        return new DoubleAdapter(getValue());
    }
}
