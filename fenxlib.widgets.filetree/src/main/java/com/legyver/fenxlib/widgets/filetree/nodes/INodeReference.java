package com.legyver.fenxlib.widgets.filetree.nodes;

import com.legyver.fenxlib.widgets.filetree.search.INamedItem;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;

/**
 * Provide the value backing a node in the tree
 */
public interface INodeReference<T> extends INamedItem {
    /**
     * Get the value the node in the tree represents
     * @return the value
     */
    T getValue();

    /**
     * Get the unique identifier for the node that will represent it in the {@link com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry}
     * @return the unique identifier
     */
    String getUniqueIdentifier();

    /**
     * Flag if the file is a directory.  This is used in ordering directories before files when under the same parent
     * @return true if the file is a directory.
     */
    boolean isDirectory();

    /**
     * Get the {@link FileTreeItem} associated with this node
     * @return the associated item displayed in the file tree
     */
    FileTreeItem getTreeNode();

    /**
     * Set the {@link FileTreeItem} associated with this node
     * @param treeItem the item displayed in the file tree
     */
    void setTreeNode(FileTreeItem treeItem);
}
