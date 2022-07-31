package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.SelectedMixin;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import javafx.scene.control.ToggleButton;

/**
 * Options for a JavaFX ToggleButton
 */
public class ToggleButtonOptions extends BaseControlBuilder<ToggleButtonOptions> implements StyleableControlOptions<ToggleButton>,
        TextMixin<ToggleButtonOptions>,
        SelectedMixin<ToggleButtonOptions> {
    private String text;
    private Boolean selected;

}
