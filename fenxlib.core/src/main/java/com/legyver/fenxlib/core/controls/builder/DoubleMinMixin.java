package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.factory.adapters.DoubleAdapter;
import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

public interface DoubleMinMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    default T min(Double min) {
        new ReflectionOperator(builder()).setValue("min", min);
        return builder();
    }

    default Double getMin() {
        return (Double) new ReflectionOperator(builder()).getValue("min");
    }

    default DoubleAdapter minAdapter() {
        return new DoubleAdapter(getMin());
    }
}
