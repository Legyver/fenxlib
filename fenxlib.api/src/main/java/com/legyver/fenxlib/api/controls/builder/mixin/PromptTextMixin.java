package com.legyver.fenxlib.api.controls.builder.mixin;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.i18n.ResourceBundleServiceRegistry;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

import java.util.Locale;

/**
 * Mixin to specify a prompt for a TextInputControl
 * To use this mixin, the builder must have a field of type String called "text"
 * @param <T> type of the builder using this mixin
 */
public interface PromptTextMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Get the prompt text
     * @return the prompt
     */
    default String getPromptText() {
        String result = (String) new ReflectionOperator(builder()).getValue("promptText");
        result = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.getDefault(), result);
        return result;
    }

    /**
     * Set the prompt text
     * @param promptText the prompt text
     * @return this builder
     */
    default T promptText(String promptText) {
        new ReflectionOperator(builder()).setValue("promptText", promptText);
        return builder();
    }
}
