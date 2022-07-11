package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

/**
 * Mixin to specify if a control is editable
 * To use this mixin, the builder must have a field of type Boolean called "editable"
 * @param <T> the type of the builder using this mixin.
 */
public interface EditableMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Specify if the control is editable
     * @param editable true if the control is editable
     * @return the builder using this mixin
     */
    default T editable(Boolean editable) {
        new ReflectionOperator(builder()).setValue("editable", editable);
        return builder();
    }

    /**
     * Get the editable flag
     * @return the editable flag
     */
    default Boolean isEditable() {
        return (Boolean) new ReflectionOperator(builder()).getValue("editable");
    }

    /**
     * Get the editable flag wrapped in an adapter
     * @return the adapter
     */
    default BooleanAdapter editableAdapter() {
        return new BooleanAdapter(isEditable());
    }
}
