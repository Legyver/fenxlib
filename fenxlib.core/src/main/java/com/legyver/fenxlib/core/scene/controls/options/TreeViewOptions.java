package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Options for a JavaFX TreeView
 */
public class TreeViewOptions extends BaseControlBuilder<TreeViewOptions> implements StyleableControlOptions<TreeView> {
    private TreeItem root;

    /**
     * Specify the root for a tree view
     * @param root the root
     * @return this builder
     */
    public TreeViewOptions root(TreeItem root) {
        this.root = root;
        return me();
    }

    /**
     * Get the root
     * @return the root
     */
    public TreeItem getRoot() {
        return root;
    }
}
