package com.legyver.fenxlib.api.controls.builder.mixin;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

/**
 * Mixin to specify if the control is disabled by default.
 * To use this mixin, the builder must have a boolean field called "disabled"
 * @param <T> the type of the builder using this mixin.
 */
public interface DisabledOptionMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    /**
     * Specify is the control is disabled by default
     * @param disabled true if the control is to be disabled by default
     * @return the builder using this mixin
     */
    default T disabled(Boolean disabled) {
        new ReflectionOperator(builder()).setValue("disabled", disabled);
        return builder();
    }

    /**
     * Get the flag for if the control is to be disabled by default
     * @return the disabled flag
     */
    default Boolean isDisabled() {
        return (Boolean) new ReflectionOperator(builder()).getValue("disabled");
    }

    /**
     * Get the disabled flag wrapped in a null-safe adapter
     * @return the adapter
     */
    default BooleanAdapter disabledAdapter() {
        return new BooleanAdapter(isDisabled());
    }
}
