package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

/**
 * Specify a tooltip for a control.
 * To use this mixin, the builder must have a field of type String called "toolTipText".
 * @param <T> the type of the builder using this mixin
 */
public interface ToolTipTextMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    /**
     * Specify the tooltip text
     * @param text the text for the tooltip
     * @return this builder
     */
    default T toolTipText(String text) {
        new ReflectionOperator(builder()).setValue("toolTipText", text);
        return builder();
    }

    /**
     * Get the tooltip text
     * @return the tooltip text
     */
    default String getToolTipText() {
        return (String) new ReflectionOperator(builder()).getValue("toolTipText");
    }
}
