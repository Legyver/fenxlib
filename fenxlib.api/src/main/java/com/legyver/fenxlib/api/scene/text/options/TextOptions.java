package com.legyver.fenxlib.api.scene.text.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.scene.text.Text;

/**
 * Options for JavaFX Text
 */
public class TextOptions extends BaseControlBuilder<TextOptions> implements StyleableControlOptions<Text>,
        TextMixin<TextOptions> {
    private String text;

}
