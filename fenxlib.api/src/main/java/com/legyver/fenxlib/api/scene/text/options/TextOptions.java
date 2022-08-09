package com.legyver.fenxlib.api.scene.text.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.scene.text.Text;

/**
 * Options for JavaFX Text
 */
public class TextOptions extends BaseControlBuilder<TextOptions> implements StyleableControlOptions<Text>,
        TextOptionMixin<TextOptions> {
    private String text;
    private Object[] textArgs;
}
