package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.ItemsOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXListView;

import java.util.List;

/**
 * Options for a MFXListView control
 */
public class MFXListViewOptions extends BaseControlBuilder<MFXListViewOptions> implements StyleableControlOptions<MFXListView>,
        ItemsOptionMixin<MFXListViewOptions> {
    private List items;
}
