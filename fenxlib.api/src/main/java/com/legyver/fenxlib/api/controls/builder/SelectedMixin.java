package com.legyver.fenxlib.api.controls.builder;

import com.legyver.fenxlib.api.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

/**
 * Mixin to specify if a control is selected by default
 * To use this mixin, the builder must have a field of type Boolean called "selected"
 * @param <T> the type of the builder using this mixin
 */
public interface SelectedMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    /**
     * Specify if the control is selected by default
     * @param selected true if the control is selected
     * @return the builder using this mixin
     */
    default T selected(Boolean selected) {
        new ReflectionOperator(builder()).setValue("selected", selected);
        return builder();
    }

    /**
     * Get the selected flag
     * @return the selected flag
     */
    default Boolean isSelected() {
        return (Boolean) new ReflectionOperator(builder()).getValue("selected");
    }

    /**
     * Get the selected flag wrapped in an adapter
     * @return the adapter
     */
    default BooleanAdapter selectedAdapter() {
        return new BooleanAdapter(isSelected());
    }

}
