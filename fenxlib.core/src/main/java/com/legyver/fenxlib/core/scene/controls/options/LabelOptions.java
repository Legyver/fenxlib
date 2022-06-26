package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.builder.TextPropertyMixin;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class LabelOptions extends BaseControlBuilder<LabelOptions> implements StyleableControlOptions<Label>,
        TextMixin<LabelOptions>,
        TextPropertyMixin<LabelOptions> {
    private StringProperty textProperty;
    private String text;
}
