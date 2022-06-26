package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.EventTargetControlOptions;
import javafx.scene.control.TreeItem;

public class TreeItemOptions extends BaseControlBuilder<TreeItemOptions> implements EventTargetControlOptions<TreeItem> {
    private Object value;

    public TreeItemOptions root(Object value) {
        this.value = value;
        return me();
    }

    public Object getValue() {
        return value;
    }
}
