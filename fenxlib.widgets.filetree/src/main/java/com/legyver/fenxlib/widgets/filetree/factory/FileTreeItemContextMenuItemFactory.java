package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

import java.util.function.BiFunction;

/**
 * Factory to produce a file tree node-specific context menu item
 */
public class FileTreeItemContextMenuItemFactory {
    /**
     * Name of the context menu item
     */
    private final String name;
    /**
     * Producer to use to create an event-handler specific to the file tree node
     */
    private final BiFunction<FileTreeRegistry, FileTreeItem, EventHandler<ActionEvent>> eventHandlerProducer;

    /**
     * Construct a factory to produce a file tree node-specific context menu item
     * @param name the name of the context menu item
     * @param eventHandlerProducer the producer that will supply the node-specific event handler for the menu-item
     */
    public FileTreeItemContextMenuItemFactory(String name, BiFunction<FileTreeRegistry, FileTreeItem, EventHandler<ActionEvent>> eventHandlerProducer) {
        this.name = name;
        this.eventHandlerProducer = eventHandlerProducer;
    }

    /**
     * Make the context menu item.
     * The disbabled property for each menu item is bound to the negative of the {@link FileTreeItem#getMenuItemEnabled(String)}
     *
     * @param fileTreeRegistry the registry for the file explorer
     * @param fileTreeItem the node in the file tree
     * @return the node-specific menu item
     */
    public MenuItem makeItem(FileTreeRegistry fileTreeRegistry, FileTreeItem fileTreeItem) {
        String itemName = fileTreeItem.getName();
        String uniqueIdentifier = fileTreeItem.getUniqueIdentifier();
        Node graphic = fileTreeItem.getGraphic();
        MenuItem menuItem = null;
        if (fileTreeItem.getMenuItemEnabled(name).get()) {
            menuItem = new MenuItem(name);
            menuItem.disableProperty().bind(fileTreeItem.getMenuItemEnabled(name).not());
            EventHandler<ActionEvent> eventHandler = eventHandlerProducer.apply(fileTreeRegistry, fileTreeItem);
            menuItem.setOnAction(eventHandler);
        }
        return menuItem;
    }
}
