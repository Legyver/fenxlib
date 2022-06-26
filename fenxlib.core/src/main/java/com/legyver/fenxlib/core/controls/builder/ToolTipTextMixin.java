package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

public interface ToolTipTextMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    default T toolTipText(String text) {
        new ReflectionOperator(builder()).setValue("toolTipText", text);
        return builder();
    }

    default String getToolTipText() {
        return (String) new ReflectionOperator(builder()).getValue("toolTipText");
    }
}
