package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.DisabledMixin;
import com.legyver.fenxlib.api.controls.builder.SelectedMixin;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import javafx.scene.control.CheckMenuItem;

/**
 * Options for a JavaFX CheckMenuItem
 */
public class CheckMenuItemOptions extends BaseControlBuilder<CheckMenuItemOptions> implements StyleableControlOptions<CheckMenuItem>,
        TextMixin<CheckMenuItemOptions>, SelectedMixin<CheckMenuItemOptions>, DisabledMixin<CheckMenuItemOptions> {
    private String text;
    private Boolean selected;
    private Boolean disabled;
}
