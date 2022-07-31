package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.SelectedMixin;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import javafx.scene.control.CheckBox;

/**
 * Options for a JavaFX CheckBox
 */
public class CheckBoxOptions extends BaseControlBuilder<CheckBoxOptions> implements StyleableControlOptions<CheckBox>,
        SelectedMixin<CheckBoxOptions>,
        TextMixin<CheckBoxOptions> {
    private String text;
    private Boolean selected;

}
