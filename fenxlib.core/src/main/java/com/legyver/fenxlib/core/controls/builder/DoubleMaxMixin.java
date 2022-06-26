package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.factory.adapters.DoubleAdapter;
import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

public interface DoubleMaxMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    default T max(Double max) {
        new ReflectionOperator(builder()).setValue("max", max);
        return builder();
    }

    default Double getMax() {
        return (Double) new ReflectionOperator(builder()).getValue("max");
    }

    default DoubleAdapter maxAdapter() {
        return new DoubleAdapter(getMax());
    }
}
