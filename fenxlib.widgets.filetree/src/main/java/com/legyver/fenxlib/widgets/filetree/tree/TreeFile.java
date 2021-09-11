package com.legyver.fenxlib.widgets.filetree.tree;

import com.legyver.fenxlib.controls.svg.SVGControl;
import com.legyver.fenxlib.icons.fa.FontAwesomeFreeSolidIcons;
import com.legyver.fenxlib.icons.fa.FontAwesomeIconFonts;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

import java.util.Collection;
import java.util.Iterator;

/**
 * Represent a filesystem file in the FileTree
 */
@SuppressWarnings("unchecked")
public class TreeFile extends FileTreeItem {
    /**
     * The color property of the file icon
     */
    private final ObjectProperty<Paint> color = new SimpleObjectProperty<>();

    /**
     * Construct a TreeFile to represent a filesystem file in the FileTree
     * @param name the name to display on the TreeItem
     * @param file the file to represent
     * @param controlIcon the control icon to use
     */
    public TreeFile(String name, SVGControl controlIcon, FileReference file) {
        super(name, controlIcon, file);
    }

    /**
     * Construct a TreeFile to represent a filesystem file in the FileTree
     * @param file the file to represent
     * @param controlIcon the control icon to use
     */
    public TreeFile(FileReference file, SVGControl controlIcon) {
        this(file.getFile().getName(), controlIcon, file);
    }

    /**
     * Construct a TreeFile to represent a filesystem file in the FileTree
     * @param file the file to represent
     */
    public TreeFile(FileReference file) {
        this(file, new SVGControl());
        SVGControl graphic = (SVGControl) getGraphic();
        graphic.setSvgIcon(FontAwesomeFreeSolidIcons.CODE);
        graphic.setSvgIconLibraryPrefix(FontAwesomeIconFonts.FONTAWESOME_FREE_SOLID);
        graphic.svgIconPaintProperty().bind(color);
        graphic.setSvgIconSize(18);
        color.set(Paint.valueOf( "#3a73cf"));
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
