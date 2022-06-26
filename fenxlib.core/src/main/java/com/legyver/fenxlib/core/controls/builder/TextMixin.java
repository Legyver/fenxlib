package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

public interface TextMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    default T text(String text) {
        new ReflectionOperator(builder()).setValue("text", text);
        return builder();
    }

    default String getText() {
        return (String) new ReflectionOperator(builder()).getValue("text");
    }

    default T useTextForName(boolean useTextForName) {
        new ReflectionOperator(builder()).setValue("useTextForName", useTextForName);
        return builder();
    }
}
