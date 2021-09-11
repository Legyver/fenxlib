package com.legyver.fenxlib.widgets.filetree.event;

import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * EventHandler to remove the selected item from the file tree
 */
public class FileTreeRemoveEventHandler implements EventHandler<ActionEvent> {
    private final FileTreeRegistry fileTreeRegistry;
    private final FileTreeItem fileTreeItem;

    /**
     * Construct an event handler to remove the selected item from the file tree
     * @param fileTreeRegistry the registry for the file tree
     * @param fileTreeItem the item to remove
     */
    public FileTreeRemoveEventHandler(FileTreeRegistry fileTreeRegistry, FileTreeItem fileTreeItem) {
        this.fileTreeRegistry = fileTreeRegistry;
        this.fileTreeItem = fileTreeItem;
    }

    @Override
    public void handle(ActionEvent event) {
        fileTreeRegistry.remove(fileTreeItem.getUniqueIdentifier());
        fileTreeItem.flagForRemoval();
        FileTreeItem parent = (FileTreeItem) fileTreeItem.getParent();
        parent.refresh();
    }
}
