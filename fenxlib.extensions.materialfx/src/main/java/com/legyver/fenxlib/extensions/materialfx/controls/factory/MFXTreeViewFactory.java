package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXTreeViewOptions;
import io.github.palexdev.materialfx.controls.MFXTreeItem;
import io.github.palexdev.materialfx.controls.MFXTreeView;

/**
 * Factory to produce a MFXTreeView
 */
public class MFXTreeViewFactory implements NodeFactory<MFXTreeView, MFXTreeViewOptions> {

    @Override
    public MFXTreeView makeNode(LocationContext locationContext, MFXTreeViewOptions options) throws CoreException {
        MFXTreeView treeView;
        if (options.getRoot() != null) {
            treeView = new MFXTreeView(options.getRoot());
        } else {
            treeView = new MFXTreeView();
        }

        return treeView;
    }

    @Override
    public MFXTreeViewOptions newOptions() {
        return new MFXTreeViewOptions();
    }
}
