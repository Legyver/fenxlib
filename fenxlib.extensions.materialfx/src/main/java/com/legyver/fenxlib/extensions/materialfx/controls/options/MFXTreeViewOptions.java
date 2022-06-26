package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTreeItem;
import io.github.palexdev.materialfx.controls.MFXTreeView;

public class MFXTreeViewOptions extends BaseControlBuilder<MFXTreeViewOptions> implements StyleableControlOptions<MFXTreeView> {
    private MFXTreeItem root;

    public MFXTreeViewOptions root(MFXTreeItem root) {
        this.root = root;
        return me();
    }

    public MFXTreeItem getRoot() {
        return root;
    }
}
