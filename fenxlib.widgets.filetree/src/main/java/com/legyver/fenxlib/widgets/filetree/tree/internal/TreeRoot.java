package com.legyver.fenxlib.widgets.filetree.tree.internal;

import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import com.legyver.fenxlib.widgets.filetree.tree.TreeFolder;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tree root node that files/folders can be associated with
 */
public class TreeRoot extends TreeFolder {

    /**
     * Construct a Tree root node that files/folders can be associated with
     *
     * @param fileReference the pseudo-reference
     */
	public TreeRoot(FileReference fileReference) {
        super(null, "", fileReference);
    }

    /**
     * Refresh children
     * @return true if this node can be removed from parent
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean refreshAndCheckIfRemovable() {
        Collection<FileTreeItem> children = getChildren();
        for (Iterator<FileTreeItem> childIt = children.iterator(); childIt.hasNext(); ) {
            FileTreeItem child = childIt.next();
            if (child.refreshAndCheckIfRemovable()) {
                childIt.remove();
            }
        }
        return false;
    }

}
