package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.SelectedOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import javafx.scene.control.ToggleButton;

/**
 * Options for a JavaFX ToggleButton
 */
public class ToggleButtonOptions extends BaseControlBuilder<ToggleButtonOptions> implements StyleableControlOptions<ToggleButton>,
        TextOptionMixin<ToggleButtonOptions>,
        SelectedOptionMixin<ToggleButtonOptions> {
    private String text;
    private Object[] textArgs;
    private Boolean selected;

}
