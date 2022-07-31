package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.EventTargetControlOptions;
import javafx.scene.control.TreeItem;

/**
 * Options for a JavaFX TreeItem
 */
public class TreeItemOptions extends BaseControlBuilder<TreeItemOptions> implements EventTargetControlOptions<TreeItem> {
    private Object value;

    /**
     * Specify the value to associate with the tree item
     * @param value the value
     * @return this builder
     */
    public TreeItemOptions value(Object value) {
        this.value = value;
        return me();
    }

    /**
     * Get the value to associate with the tree item
     * @return the value
     */
    public Object getValue() {
        return value;
    }
}
