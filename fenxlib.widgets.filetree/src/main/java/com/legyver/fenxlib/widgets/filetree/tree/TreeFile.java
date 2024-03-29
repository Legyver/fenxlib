package com.legyver.fenxlib.widgets.filetree.tree;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.icons.application.IconAliasMap;
import com.legyver.fenxlib.controls.icon.IconControl;
import com.legyver.fenxlib.api.icons.options.IconOptions;
import com.legyver.fenxlib.widgets.filetree.icons.TreeNodeType;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import javafx.scene.control.TreeView;

import java.util.Collection;
import java.util.Iterator;

/**
 * Represent a filesystem file in the FileTree
 */
@SuppressWarnings("unchecked")
public class TreeFile extends FileTreeItem {

    /**
     * Construct a TreeFile to represent a filesystem file in the FileTree
     *
     * @param treeView the tree view the tree file belongs to
     * @param name        the name to display on the TreeItem
     * @param controlIcon the control icon to use
     * @param file        the file to represent
     */
    public TreeFile(TreeView treeView, String name, IconControl controlIcon, FileReference file) {
        super(treeView, name, controlIcon, file);
    }

    /**
     * Construct a TreeFile to represent a filesystem file in the FileTree
     *
     * @param treeView the tree view the tree file belongs to
     * @param file        the file to represent
     * @param controlIcon the control icon to use
     */
    public TreeFile(TreeView treeView, FileReference file, IconControl controlIcon) {
        this(treeView, file.getFile().getName(), controlIcon, file);
    }

    /**
     * Construct a TreeFile to represent a filesystem file in the FileTree
     *
     * @param treeView the tree view the tree file belongs to
     * @param file     the file to represent
     */
    public TreeFile(TreeView treeView, FileReference file) {
        this(treeView, file, new IconControl());
        IconControl graphic = (IconControl) getGraphic();
        if (graphic != null) {
            IconAliasMap iconAliasMap = ApplicationContext.getIconAliasMap();
            IconOptions iconOptions = iconAliasMap.lookupIconOptions(TreeNodeType.FILE, file.getName());
            graphic.setIconOptions(iconOptions);
        }
    }

    /**
     * Refresh children
     * @return true if this node can be removed from parent
     */
    @Override
    public boolean refreshAndCheckIfRemovable() {
        boolean result = false;
        if (canBeRemoved || !((FileReference) nodeReference).getFile().exists()) {
            //remove this node from the tree
            result = true;
        } else {
            //check all child nodes to see if they are still valid
            @SuppressWarnings("unchecked")
            Collection<FileTreeItem> children = getChildren();
            for (Iterator<FileTreeItem> childIt = children.iterator(); childIt.hasNext(); ) {
                FileTreeItem child = childIt.next();
                if (child.refreshAndCheckIfRemovable()) {
                    childIt.remove();
                }
            }
        }
        return result;
    }
}
