package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTreeItem;
import io.github.palexdev.materialfx.controls.MFXTreeView;

/**
 * Options for a MFXTreeView control
 */
public class MFXTreeViewOptions extends BaseControlBuilder<MFXTreeViewOptions> implements StyleableControlOptions<MFXTreeView> {
    private MFXTreeItem root;

    /**
     * Specify the tree view root
     * @param root the root
     * @return this builder
     */
    public MFXTreeViewOptions root(MFXTreeItem root) {
        this.root = root;
        return me();
    }

    /**
     * Get the root for the tree
     * @return the root
     */
    public MFXTreeItem getRoot() {
        return root;
    }
}
