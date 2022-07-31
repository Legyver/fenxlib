package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import com.legyver.fenxlib.api.controls.builder.TextPropertyMixin;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

/**
 * Options for a JavaFX Label
 */
public class LabelOptions extends BaseControlBuilder<LabelOptions> implements StyleableControlOptions<Label>,
        TextMixin<LabelOptions>,
        TextPropertyMixin<LabelOptions> {
    private StringProperty textProperty;
    private String text;
}
