package com.legyver.fenxlib.widgets.filetree.scan;

import com.legyver.fenxlib.widgets.filetree.factory.FileTreeRemoveEventHandlerFactory;
import com.legyver.fenxlib.widgets.filetree.factory.TreeItemChildFactory;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import com.legyver.fenxlib.widgets.filetree.tree.TreeFile;
import com.legyver.fenxlib.widgets.filetree.tree.internal.TreeRoot;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Handle filesystem CREATE events triggered when a watched folder has a directory or file created under it.
 */
public class FileSystemCreateEventHandler implements FileSystemEventHandler {
    private TreeItemChildFactory childFactory;

    /**
     * Handle CREATE events.
     * If the parent file tree item is the tree root, the {@link FileTreeRemoveEventHandlerFactory#MENU_ITEM_NAME} is set to true.
     * @param fileTreeRegistry the registry to register the file with in the event of a CREATE event
     * @param fileSystemEvent the event information
     */
    @Override
    public void handle(FileTreeRegistry fileTreeRegistry, FileSystemEvent fileSystemEvent) {
        IFileReference parentFileReference = fileSystemEvent.getParentFileReference();
        IFileReference createdChildFileReference = fileSystemEvent.getFileReference();
        FileTreeItem fileTreeItem = childFactory.makeNode(fileTreeRegistry, parentFileReference.getTreeNode(), createdChildFileReference);
        FileTreeItem parentFileTreeItem = parentFileReference.getTreeNode();
        parentFileTreeItem.addChild(fileTreeItem);
        parentFileTreeItem.refresh();
    }

    /**
     * Set the ChildFactory on the CREATE handler.  This is used to create the appropriate node in the tree when a file is added/discovered.
     * @param childFactory the factory to use
     */
    public void setChildFactory(TreeItemChildFactory childFactory) {
        this.childFactory = childFactory;
    }
}
