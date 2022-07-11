package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.ItemsMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXListView;

import java.util.List;

/**
 * Options for a MFXListView control
 */
public class MFXListViewOptions extends BaseControlBuilder<MFXListViewOptions> implements StyleableControlOptions<MFXListView>,
        ItemsMixin<MFXListViewOptions> {
    private List items;
}
