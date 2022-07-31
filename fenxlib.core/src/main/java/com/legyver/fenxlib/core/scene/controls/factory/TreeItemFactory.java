package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.EventTargetFactory;
import com.legyver.fenxlib.api.scene.controls.options.TreeItemOptions;
import javafx.scene.control.TreeItem;

/**
 * Factory to create a tree item
 */
@SuppressWarnings("unchecked")
public class TreeItemFactory implements EventTargetFactory<TreeItem, TreeItemOptions> {

    @Override
    public TreeItem makeNode(LocationContext locationContext, TreeItemOptions options) throws CoreException {
        TreeItem treeItem = makeTreeItem();
        treeItem.setValue(options.getValue());
        Fenxlib.register(locationContext.decorateWith(options.getName()), treeItem);
        return treeItem;
    }

    @Override
    public TreeItemOptions newOptions() {
        return new TreeItemOptions();
    }

    /**
     * Factory method to instantiate a TreeItem
     * @return plain javafx {@link TreeItem}.  Override if you want something else
     */
    protected TreeItem makeTreeItem() {
        return new TreeItem();
    }
}
