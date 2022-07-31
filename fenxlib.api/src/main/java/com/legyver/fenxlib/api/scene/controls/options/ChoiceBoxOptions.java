package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.ItemsMixin;
import javafx.scene.control.ChoiceBox;

import java.util.List;

/**
 * Options for a JavaFX ChoiceBox
 */
public class ChoiceBoxOptions extends BaseControlBuilder<ChoiceBoxOptions> implements StyleableControlOptions<ChoiceBox>,
        ItemsMixin<ChoiceBoxOptions> {
    private List items;

}
