package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.TreeViewOptions;
import javafx.scene.control.TreeView;

/**
 * Factory to create a tree view
 */
@SuppressWarnings("unchecked")
public class TreeViewFactory implements NodeFactory<TreeView, TreeViewOptions> {

    @Override
    public TreeView makeNode(LocationContext locationContext, TreeViewOptions options) throws CoreException {
        TreeView treeView = makeTreeView();
        treeView.setRoot(options.getRoot());
        Fenxlib.register(locationContext.decorateWith(options.getName()), treeView);
        return treeView;
    }

    @Override
    public TreeViewOptions newOptions() {
        return new TreeViewOptions();
    }

    /**
     * Factory method to instantiate a TreeView
     * @return plain javafx {@link TreeView}.  Override if you want something else
     */
    protected TreeView makeTreeView() {
        return new TreeView();
    }
}
