package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.EditableOptionMixin;
import javafx.scene.control.ListView;

/**
 * Options for a JavaFX ListView
 */
public class ListViewOptions extends BaseControlBuilder<ListViewOptions> implements StyleableControlOptions<ListView>,
        EditableOptionMixin<ListViewOptions> {
    private Boolean editable;
}
