package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXTreeItem;
import io.github.palexdev.materialfx.controls.MFXTreeView;

/**
 * Factory to produce a MFXTreeView
 * @param <T> the type of data associated with the tree view
 */
public class MFXTreeViewFactory<T> implements NodeFactory<MFXTreeView<T>> {
    /**
     * Constructor param to specify a root for the tree view
     */
    public static final String CONSTRUCTOR_PARAM_ROOT = "root";

    private final MFXTreeItem<T> root;

    /**
     * Construct a factory using the specified tree item as the root
     * @param root the root for the tree view
     */
    public MFXTreeViewFactory(MFXTreeItem<T> root) {
        this.root = root;
    }

    @Override
    public MFXTreeView<T> makeNode(LocationContext locationContext) throws CoreException {
        MFXTreeView treeView;
        if (root != null) {
            treeView = new MFXTreeView(root);
        } else {
            treeView = new MFXTreeView();
        }

        return treeView;
    }
}
