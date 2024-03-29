package com.legyver.fenxlib.api.controls.builder.mixin;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.factory.adapters.DoubleAdapter;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

/**
 * Mixin to specify a maximum value as a double.
 * To use this mixin, the builder must have a field of type Double called "max"
 * @param <T> the type of the builder using this mixin
 */
public interface DoubleMaxOptionMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Speficy the max value for the control
     * @param max the max
     * @return the builder using this mixin
     */
    default T max(Double max) {
        new ReflectionOperator(builder()).setValue("max", max);
        return builder();
    }

    /**
     * Get the max value
     * @return the max value
     */
    default Double getMax() {
        return (Double) new ReflectionOperator(builder()).getValue("max");
    }

    /**
     * Get the max value wrapped in an adapter
     * @return the adapter
     */
    default DoubleAdapter maxAdapter() {
        return new DoubleAdapter(getMax());
    }
}
