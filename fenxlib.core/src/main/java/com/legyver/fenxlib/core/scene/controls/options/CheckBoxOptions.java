package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.SelectedMixin;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
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
