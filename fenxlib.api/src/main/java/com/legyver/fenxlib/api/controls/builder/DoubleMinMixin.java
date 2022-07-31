package com.legyver.fenxlib.api.controls.builder;

import com.legyver.fenxlib.api.factory.adapters.DoubleAdapter;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

/**
 * Mixin to specify the minimum value for a control
 * To use this mixin, the builder must have a field of type Double called "min"
 * @param <T> the type of the builder using this mixin
 */
public interface DoubleMinMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Specify the minimum value
     * @param min the minimum value
     * @return the builder using this mixin
     */
    default T min(Double min) {
        new ReflectionOperator(builder()).setValue("min", min);
        return builder();
    }

    /**
     * Get the minimum value
     * @return the minimum value
     */
    default Double getMin() {
        return (Double) new ReflectionOperator(builder()).getValue("min");
    }

    /**
     * Get the minimum value wrapped in an adapter
     * @return the adapter
     */
    default DoubleAdapter minAdapter() {
        return new DoubleAdapter(getMin());
    }
}
