package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.mixin.PromptTextMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import javafx.scene.control.PasswordField;

/**
 * Options for a JavaFX PasswordField
 */
public class PasswordFieldOptions extends BaseControlBuilder<PasswordFieldOptions> implements StyleableControlOptions<PasswordField>,
    PromptTextMixin<PasswordFieldOptions> {
    private String promptText;

}
