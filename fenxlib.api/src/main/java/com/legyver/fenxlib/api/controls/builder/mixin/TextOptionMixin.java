package com.legyver.fenxlib.api.controls.builder.mixin;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.i18n.ResourceBundleServiceRegistry;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

import java.util.Locale;

/**
 * Mixin to specify the text for a control.
 * To use this mixin, the builder must have a field of type String called "text"
 * To avoid warnings, it is also wise to add an Object[] field called "textArgs" used for resolving property argument references
 * @param <T> the type of the builder using this mixin
 */
public interface TextOptionMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Specify the text for a control
     * @param text the text
     * @param args optional arguments when resolving an i18n property for the text
     * @return the builder using this mixin
     */
    default T text(String text, Object...args) {
        new ReflectionOperator(builder()).setValue("text", text);
        if (args != null) {
            new ReflectionOperator(builder()).setValue("textArgs", args);
        }
        return builder();
    }

    /**
     * Get the text.
     * If there are textArgs specified and the text field contains an i18n property, then the args will be evaluated
     * after resolving the property
     * @return the text
     */
    default String getText() {
        String result = (String) new ReflectionOperator(builder()).getValue("text");
        Object[] textArgs = (Object[]) new ReflectionOperator(builder()).getValue("textArgs");
        result = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.getDefault(), result, textArgs);
        return result;
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
