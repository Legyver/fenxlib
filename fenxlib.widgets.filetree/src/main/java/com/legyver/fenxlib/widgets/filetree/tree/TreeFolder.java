package com.legyver.fenxlib.widgets.filetree.tree;

import com.legyver.fenxlib.controls.svg.SVGControl;
import com.legyver.fenxlib.icons.fa.FontAwesomeFreeSolidIcons;
import com.legyver.fenxlib.icons.fa.FontAwesomeIconFonts;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

    private TreeFolder(String label, SVGControl graphic, IFileReference fileReference) {
        super(label, graphic, fileReference);
        if (graphic != null) {
            graphic.setSvgIcon(FontAwesomeFreeSolidIcons.FOLDER);
            graphic.setSvgIconLibraryPrefix(FontAwesomeIconFonts.FONTAWESOME_FREE_SOLID);
            graphic.svgIconPaintProperty().bind(color);
            graphic.setSvgIconSize(16);
            color.set(Paint.valueOf("#e7c9a9"));
        }
    }

    /**
     * Construct a TreeItem for a filesystem folder
     * @param label the label for the tree folder
     * @param fileReference the filesystem folder reference
     */
    public TreeFolder(String label, IFileReference fileReference) {
        this(label, new SVGControl(), fileReference);
    }

    /**
     * Construct a TreeItem for a filesystem folder.
     * Uses the file's simple name for the label.
     *
     * @param fileReference the filesystem folder reference
     */
    public TreeFolder(FileReference fileReference) {
        this(fileReference.getFile().getName(), fileReference);
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