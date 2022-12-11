package com.legyver.fenxlib.widgets.filetree.event;

import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeView;

import java.util.HashSet;
import java.util.Set;

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
        TreeView treeView = fileTreeItem.getTreeView();
        MultipleSelectionModel selectionModel = treeView.getSelectionModel();
        @SuppressWarnings("unchecked")
        ObservableList<FileTreeItem> selectedItems = selectionModel.getSelectedItems();

        //parent instance uniqueness
        Set<FileTreeItem> parents = new HashSet<>();
        for (FileTreeItem selectedItem : selectedItems) {
            fileTreeRegistry.remove(selectedItem.getUniqueIdentifier());
            selectedItem.flagForRemoval();
            FileTreeItem parent = (FileTreeItem) fileTreeItem.getParent();
            parents.add(parent);
        }
        //delayed refresh so we don't double-dip on parents
        for (FileTreeItem parent : parents) {
            parent.refresh();
        }
    }
}
