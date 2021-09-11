package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import java.util.stream.Stream;

/**
 * Factory to produce a context menu for a node in a file tree
 */
public class FileTreeItemContextMenuFactory {
    /**
     * Factories to provide the individual items in the context menu
     */
    private final FileTreeItemContextMenuItemFactory[] factories;
    /**
     * File tree registry to pass to the {@link FileTreeItemContextMenuItemFactory}
     */
    private FileTreeRegistry fileTreeRegistry;

    /**
     * Construct a factory to produce a context menu for a node in a file tree
     * @param factories the factories to produce the menu items
     */
    public FileTreeItemContextMenuFactory(FileTreeItemContextMenuItemFactory...factories) {
        this.factories = factories;
    }

    /**
     * Create a context menu with the items produced by the {@link #factories}
     * @param fileTreeItem the item in the file tree
     * @return the produced context menu for the node
     */
    public ContextMenu makeContextMenu(FileTreeItem fileTreeItem) {
        ContextMenu contextMenu = new ContextMenu();
        ObservableList<MenuItem> items = contextMenu.getItems();
        if (factories != null) {
            Stream.of(factories).forEach(f-> {
                MenuItem menuItem = f.makeItem(fileTreeRegistry, fileTreeItem);
                if (menuItem != null) {
                    items.add(menuItem);
                }
            });
        }
        return contextMenu;
    }

    /**
     * Set the file tree registry
     * @param fileTreeRegistry the file tree registry
     */
    public void setFileTreeRegistry(FileTreeRegistry fileTreeRegistry) {
        this.fileTreeRegistry = fileTreeRegistry;
    }
}
