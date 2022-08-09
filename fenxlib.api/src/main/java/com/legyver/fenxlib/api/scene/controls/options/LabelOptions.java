package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextPropertyOptionMixin;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

/**
 * Options for a JavaFX Label
 */
public class LabelOptions extends BaseControlBuilder<LabelOptions> implements StyleableControlOptions<Label>,
        TextOptionMixin<LabelOptions>,
        TextPropertyOptionMixin<LabelOptions> {
    private StringProperty textProperty;
    private String text;
    private Object[] textArgs;
}
