package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Factory to create a tree view
 */
@SuppressWarnings("unchecked")
public class TreeViewFactory implements NodeFactory<TreeView> {
    /**
     * Param to specify the root of the tree view
     */
    public static final String CONSTRUCTOR_PARAM_ROOT = "root";

    private final TreeItem root;

    /**
     * Construct a factory to produce a Tree View
     * @param root the root of the tree
     */
    public TreeViewFactory(TreeItem root) {
        this.root = root;
    }

    @Override
    public TreeView makeNode(LocationContext locationContext) throws CoreException {
        TreeView treeView = makeTreeView();
        treeView.setRoot(root);
        Fenxlib.register(locationContext, treeView);
        return treeView;
    }

    /**
     * Factory method to instantiate a TreeView
     * @return plain javafx {@link TreeView}.  Override if you want something else
     */
    protected TreeView makeTreeView() {
        return new TreeView();
    }
}
