package com.legyver.fenxlib.core.scene.text.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import javafx.scene.text.Text;

/**
 * Options for JavaFX Text
 */
public class TextOptions extends BaseControlBuilder<TextOptions> implements StyleableControlOptions<Text>,
        TextMixin<TextOptions> {
    private String text;

}
