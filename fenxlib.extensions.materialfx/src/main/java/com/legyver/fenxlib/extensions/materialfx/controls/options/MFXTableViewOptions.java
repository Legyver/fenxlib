package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.ItemsMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTableView;

import java.util.List;

/**
 * Options for a MFXTableView control
 */
public class MFXTableViewOptions extends BaseControlBuilder<MFXTableViewOptions> implements StyleableControlOptions<MFXTableView>,
        ItemsMixin<MFXTableViewOptions> {

    private List items;
}
