package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXPasswordField;

/**
 * Options for a MFXPasswordField control
 */
public class MFXPasswordFieldOptions extends BaseControlBuilder<MFXPasswordFieldOptions> implements StyleableControlOptions<MFXPasswordField>, TextMixin<MFXPasswordFieldOptions> {
    private String text;
    private String promptText;
    private String floatingText;

    /**
     * Specify the prompt text
     * @param promptText the prompt text
     * @return this builder
     */
    public MFXPasswordFieldOptions promptText(String promptText) {
        this.promptText = promptText;
        return me();
    }

    /**
     * Get the prompt text
     * @return the prompt text
     */
    public String getPromptText() {
        return promptText;
    }

    /**
     * Specify the floating text
     * @param floatingText the floating text
     * @return this builder
     */
    public MFXPasswordFieldOptions floatingText(String floatingText) {
        this.floatingText = floatingText;
        return me();
    }

    /**
     * Get the floating text
     * @return the floating text
     */
    public String getFloatingText() {
        return floatingText;
    }
}
