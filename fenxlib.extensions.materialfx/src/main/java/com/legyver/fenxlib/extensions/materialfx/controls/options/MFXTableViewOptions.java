package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.ItemsOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTableView;

import java.util.List;

/**
 * Options for a MFXTableView control
 */
public class MFXTableViewOptions extends BaseControlBuilder<MFXTableViewOptions> implements StyleableControlOptions<MFXTableView>,
        ItemsOptionMixin<MFXTableViewOptions> {

    private List items;
}
