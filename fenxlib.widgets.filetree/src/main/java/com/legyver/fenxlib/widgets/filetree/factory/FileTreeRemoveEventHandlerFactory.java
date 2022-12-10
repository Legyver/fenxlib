package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.fenxlib.widgets.filetree.event.FileTreeRemoveEventHandler;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MultipleSelectionModel;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

/**
 * Factory to produce an event handler that removes an item from the file tree
 */
public class FileTreeRemoveEventHandlerFactory implements BiFunction<FileTreeRegistry, FileTreeItem, EventHandler<ActionEvent>> {
    /**
     * Name of Menu Item or property for i18n purposes
     */
    public static final String MENU_ITEM_NAME = "legyver.defaults.label.filetree.onfile.menu.remove";
    /**
     * Make the remove event handler for top-level entries in the tree.
     * If the item is not a top-level entry, the event handler is null.
     * @param fileTreeRegistry the registry for the file explorer tree
     * @param fileTreeItem the item in the file explorer tree
     * @return the event handler
     */
    @Override
    public EventHandler<ActionEvent> apply(FileTreeRegistry fileTreeRegistry, FileTreeItem fileTreeItem) {
        return new FileTreeRemoveEventHandler(fileTreeRegistry, fileTreeItem);
    }

}
