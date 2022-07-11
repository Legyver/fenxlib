package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.EditableMixin;
import javafx.scene.control.ListView;

/**
 * Options for a JavaFX ListView
 */
public class ListViewOptions extends BaseControlBuilder<ListViewOptions> implements StyleableControlOptions<ListView>,
        EditableMixin<ListViewOptions> {
    private Boolean editable;
}
