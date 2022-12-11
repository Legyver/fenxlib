package com.legyver.fenxlib.widgets.filetree.tree;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.icons.application.IconAliasMap;
import com.legyver.fenxlib.api.icons.options.IconOptions;
import com.legyver.fenxlib.controls.icon.IconToggleControl;
import com.legyver.fenxlib.widgets.filetree.icons.TreeNodeType;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Paint;

import java.util.Collection;
import java.util.Iterator;

/**
 * A TreeItem to represent a filesystem folder
 */
public class TreeFolder extends FileTreeItem<IFileReference> {

    /**
     * The color of the folder icon
     */
    private final ObjectProperty<Paint> color = new SimpleObjectProperty<>();

    private TreeFolder(TreeView treeView, String label, IconToggleControl graphic, IFileReference fileReference) {
        super(treeView, label, graphic, fileReference);
        if (graphic != null) {
            IconAliasMap iconAliasMap = ApplicationContext.getIconAliasMap();

            IconOptions closedIconOptions = iconAliasMap.lookupIconOptions(TreeNodeType.DIRECTORY, false);
            IconOptions expandedIconOptions = iconAliasMap.lookupIconOptions(TreeNodeType.DIRECTORY, true);

            graphic.setIconOptions(closedIconOptions);
            graphic.setAlternateIconName(expandedIconOptions.getIcon());
            graphic.iconPaintProperty().bind(color);
            graphic.setIconSize(closedIconOptions.getIconSize());
            graphic.showAlternateProperty().bind(expandedProperty());
            color.set(Paint.valueOf("#e7c9a9"));
        }
    }

    /**
     * Construct a TreeItem for a filesystem folder
     * @param treeView  the tree view the tree file belongs to
     * @param label the label for the tree folder
     * @param fileReference the filesystem folder reference
     */
    public TreeFolder(TreeView treeView, String label, IFileReference fileReference) {
        this(treeView, label, new IconToggleControl(), fileReference);
    }

    /**
     * Construct a TreeItem for a filesystem folder.
     * Uses the file's simple name for the label.
     * @param treeView the tree view the tree file belongs to
     * @param fileReference the filesystem folder reference
     */
    public TreeFolder(TreeView treeView, FileReference fileReference) {
        this(treeView, fileReference.getFile().getName(), fileReference);
    }

    /**
     * Refresh children
     * @return true if this node can be removed from parent
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean refreshAndCheckIfRemovable() {
        boolean deleteMe = canBeRemoved || !((IFileReference) nodeReference).getFile().exists();
        if (!deleteMe) {
            //prune any children that no longer exist
            Collection<FileTreeItem> children = getChildren();
            for (Iterator<FileTreeItem> childIt = children.iterator(); childIt.hasNext(); ) {
                FileTreeItem child = childIt.next();
                boolean childRemoved = child.refreshAndCheckIfRemovable();
                if (childRemoved) {
                    childIt.remove();
                }
            }
        }
        return deleteMe;
    }

    /**
     * Get the color of the icon
     * @return the color
     */
    public Paint getColor() {
        return color.get();
    }

    /**
     * The color property for the icon
     * @return the color property
     */
    public ObjectProperty<Paint> colorProperty() {
        return color;
    }

    /**
     * Set the color for the icon
     * @param color the color
     */
    public void setColor(Paint color) {
        this.color.set(color);
    }
}
