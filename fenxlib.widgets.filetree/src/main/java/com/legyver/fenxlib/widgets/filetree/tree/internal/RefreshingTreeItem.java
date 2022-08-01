package com.legyver.fenxlib.widgets.filetree.tree.internal;

import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;

/**
 * Placeholder message to display in tree
 */
@SuppressWarnings("unchecked")
public class RefreshingTreeItem extends FileTreeItem {

    /**
     * Construct a tree node to display placeholder text while the tree is refreshing.
     * This can also be an i18n message property
     * @param message the message to display
     */
    public RefreshingTreeItem(String message) {
        super(i18n(message), null, null);
    }

    /**
     * Construct a tree node to display a 'Refreshing' message in the tree while refreshing
     */
    public RefreshingTreeItem() {
        this("legyver.defaults.label.filetree.refreshing");
    }

    /**
     * NOOP
     */
    protected void addFauxChild() {
        //noop
    }

    /**
     * Always returns true because this is only intended to be present when the tree is refreshing
     * @return true
     */
    @Override
    public boolean refreshAndCheckIfRemovable() {
        return true;
    }
}
