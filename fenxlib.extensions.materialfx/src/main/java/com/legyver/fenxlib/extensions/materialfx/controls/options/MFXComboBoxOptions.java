package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.ItemsMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXComboBox;

import java.util.List;

/**
 * Options for a MFXComboBox control
 */
public class MFXComboBoxOptions extends BaseControlBuilder<MFXComboBoxOptions> implements StyleableControlOptions<MFXComboBox>,
        ItemsMixin<MFXComboBoxOptions> {
    private List items;

}
