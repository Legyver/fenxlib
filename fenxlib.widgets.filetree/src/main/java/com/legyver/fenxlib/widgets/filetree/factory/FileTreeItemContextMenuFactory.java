package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Factory to produce a context menu for a node in a file tree
 */
public class FileTreeItemContextMenuFactory {
    /**
     * Factories to provide the individual items in the context menu
     */
    private final List<FileTreeItemContextMenuItemFactory> factories;
    /**
     * File tree registry to pass to the {@link FileTreeItemContextMenuItemFactory}
     */
    private FileTreeRegistry fileTreeRegistry;

    /**
     * Construct a factory to produce a context menu for a node in a file tree
     * @param factories the factories to produce the menu items
     */
    public FileTreeItemContextMenuFactory(FileTreeItemContextMenuItemFactory...factories) {
        if (factories != null) {
            this.factories = Arrays.stream(factories).collect(Collectors.toList());
        } else {
            this.factories = new ArrayList<>();
        }
    }

    /**
     * Add a menu item factory
     * @param factory factory to use to construct a menu item
     */
    public void addMenuItemFactory(FileTreeItemContextMenuItemFactory factory) {
        this.factories.add(factory);
    }

    /**
     * Create a context menu with the items produced by the {@link #factories}
     * @param fileTreeItem the item in the file tree
     * @return the produced context menu for the node
     */
    public ContextMenu makeContextMenu(FileTreeItem fileTreeItem) {
        ContextMenu contextMenu = new ContextMenu();
        ObservableList<MenuItem> items = contextMenu.getItems();
        factories.forEach(f-> {
            MenuItem menuItem = f.makeItem(fileTreeRegistry, fileTreeItem);
            if (menuItem != null) {
                items.add(menuItem);
            }
        });
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
