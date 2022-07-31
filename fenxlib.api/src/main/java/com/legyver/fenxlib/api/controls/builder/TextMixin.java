package com.legyver.fenxlib.api.controls.builder;

import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

/**
 * Mixin to specify the text for a control.
 * To use this mixin, the builder must have a field of type String called "text"
 * @param <T> the type of the builder using this mixin
 */
public interface TextMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Specify the text for a control
     * @param text the text
     * @return the builder using this mixin
     */
    default T text(String text) {
        new ReflectionOperator(builder()).setValue("text", text);
        return builder();
    }

    /**
     * Get the text
     * @return the text
     */
    default String getText() {
        return (String) new ReflectionOperator(builder()).getValue("text");
    }

    /**
     * Specify if the text should also be used as the name, if this is set to true, the control will be registered under the parent using the default text
     * @param useTextForName true if the text should be used as the name
     * @return this builder
     */
    default T useTextForName(boolean useTextForName) {
        new ReflectionOperator(builder()).setValue("useTextForName", useTextForName);
        return builder();
    }
}
