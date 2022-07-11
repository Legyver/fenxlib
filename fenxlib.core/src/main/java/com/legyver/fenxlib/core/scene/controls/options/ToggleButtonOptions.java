package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.SelectedMixin;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
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
