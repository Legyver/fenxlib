package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.DisabledOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.SelectedOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import javafx.scene.control.CheckMenuItem;

/**
 * Options for a JavaFX CheckMenuItem
 */
public class CheckMenuItemOptions extends BaseControlBuilder<CheckMenuItemOptions> implements StyleableControlOptions<CheckMenuItem>,
        TextOptionMixin<CheckMenuItemOptions>, SelectedOptionMixin<CheckMenuItemOptions>, DisabledOptionMixin<CheckMenuItemOptions> {
    private String text;
    private Object[] textArgs;
    private Boolean selected;
    private Boolean disabled;
}
