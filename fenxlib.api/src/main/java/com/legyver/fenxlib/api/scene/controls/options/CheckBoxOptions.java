package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.SelectedOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import javafx.scene.control.CheckBox;

/**
 * Options for a JavaFX CheckBox
 */
public class CheckBoxOptions extends BaseControlBuilder<CheckBoxOptions> implements StyleableControlOptions<CheckBox>,
        SelectedOptionMixin<CheckBoxOptions>,
        TextOptionMixin<CheckBoxOptions> {
    private String text;
    private Object[] textArgs;
    private Boolean selected;

}
