package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewOptions extends BaseControlBuilder<TreeViewOptions> implements StyleableControlOptions<TreeView> {
    private TreeItem root;

    public TreeViewOptions root(TreeItem root) {
        this.root = root;
        return me();
    }

    public TreeItem getRoot() {
        return root;
    }
}
