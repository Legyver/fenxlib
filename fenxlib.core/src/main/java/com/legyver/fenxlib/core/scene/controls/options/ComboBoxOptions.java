package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.ItemsMixin;
import javafx.scene.control.ComboBox;

import java.util.List;

public class ComboBoxOptions extends BaseControlBuilder<ComboBoxOptions> implements StyleableControlOptions<ComboBox>,
        ItemsMixin<ComboBoxOptions> {
    private List items;
}
