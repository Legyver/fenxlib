package com.legyver.fenxlib.api.controls.builder;

import com.legyver.fenxlib.api.factory.adapters.DoubleAdapter;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

/**
 * Mixin to specify the value.
 * To use this mixin, the builder must have a field of type Double called "value"
 * @param <T> the type of the builder using this mixin
 */
public interface DoubleValueMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Specify the default value of the control
     * @param value the value
     * @return the builder using this mixin
     */
    default T value(Double value) {
        new ReflectionOperator(builder()).setValue("value", value);
        return builder();
    }

    /**
     * Get the value
     * @return the value
     */
    default Double getValue() {
        return (Double) new ReflectionOperator(builder()).getValue("value");
    }

    /**
     * Get the value wrapped in an adapter.
     * @return the adapter
     */
    default DoubleAdapter valueAdapter() {
        return new DoubleAdapter(getValue());
    }
}
