package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.ItemsMixin;
import javafx.scene.control.ChoiceBox;

import java.util.List;

public class ChoiceBoxOptions extends BaseControlBuilder<ChoiceBoxOptions> implements StyleableControlOptions<ChoiceBox>,
        ItemsMixin<ChoiceBoxOptions> {
    private List items;

}
