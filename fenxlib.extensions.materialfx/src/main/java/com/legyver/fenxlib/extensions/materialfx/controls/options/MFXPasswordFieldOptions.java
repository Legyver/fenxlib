package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXPasswordField;

public class MFXPasswordFieldOptions extends BaseControlBuilder<MFXPasswordFieldOptions> implements StyleableControlOptions<MFXPasswordField>, TextMixin<MFXPasswordFieldOptions> {
    private String text;
    private String promptText;
    private String floatingText;

    public MFXPasswordFieldOptions promptText(String promptText) {
        this.promptText = promptText;
        return me();
    }

    public String getPromptText() {
        return promptText;
    }

    public MFXPasswordFieldOptions floatingText(String floatingText) {
        this.floatingText = floatingText;
        return me();
    }

    public String getFloatingText() {
        return floatingText;
    }
}
