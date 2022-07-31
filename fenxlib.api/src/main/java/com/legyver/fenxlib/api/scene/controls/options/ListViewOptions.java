package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.EditableMixin;
import javafx.scene.control.ListView;

/**
 * Options for a JavaFX ListView
 */
public class ListViewOptions extends BaseControlBuilder<ListViewOptions> implements StyleableControlOptions<ListView>,
        EditableMixin<ListViewOptions> {
    private Boolean editable;
}
