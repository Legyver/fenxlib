package com.legyver.fenxlib.widgets.filetree.tree;

import com.legyver.fenxlib.controls.icon.IconControl;
import com.legyver.fenxlib.core.icons.options.IconOptions;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.service.FileTreeIconRegistry;
import javafx.beans.property.ObjectProperty;
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
    public TreeFile(String name, IconControl controlIcon, FileReference file) {
        super(name, controlIcon, file);
    }

    /**
     * Construct a TreeFile to represent a filesystem file in the FileTree
     * @param file the file to represent
     * @param controlIcon the control icon to use
     */
    public TreeFile(FileReference file, IconControl controlIcon) {
        this(file.getFile().getName(), controlIcon, file);
    }

    /**
     * Construct a TreeFile to represent a filesystem file in the FileTree
     * @param file the file to represent
     */
    public TreeFile(FileReference file) {
        this(file, new IconControl());
        IconControl graphic = (IconControl) getGraphic();
        IconOptions iconOptions = FileTreeIconRegistry.getInstance().getIcon(file);
        graphic.setIconOptions(iconOptions);
        graphic.iconPaintProperty().bind(color);
        color.set(Paint.valueOf(iconOptions.getIconColorString()));
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
