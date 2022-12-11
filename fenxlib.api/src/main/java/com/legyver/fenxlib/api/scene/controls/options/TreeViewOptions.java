package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Options for a JavaFX TreeView
 */
public class TreeViewOptions extends BaseControlBuilder<TreeViewOptions> implements StyleableControlOptions<TreeView> {
    private TreeItem root;
    private SelectionMode selectionMode = SelectionMode.SINGLE;

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

    /**
     * Specify the selection mode
     * @param selectionMode the selection mode
     * @return this builder
     */
    public TreeViewOptions selectionMode(SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
        return me();
    }

    /**
     * Get the specified selection mode, by default {@link SelectionMode#SINGLE}
     * @return selection mode
     */
    public SelectionMode getSelectionMode() {
        return selectionMode;
    }
}
