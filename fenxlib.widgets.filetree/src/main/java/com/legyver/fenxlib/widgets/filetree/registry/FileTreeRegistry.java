package com.legyver.fenxlib.widgets.filetree.registry;

import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.INodeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

/**
 * A Registry of files shown in the tree
 */
public class FileTreeRegistry<T extends INodeReference> {
    /**
     * All items in the tree.
     */
    private final ObservableList<T> items = FXCollections.observableArrayList();
    /**
     * All files in the tree.
     */
    private final ObservableList<IFileReference> files = FXCollections.observableArrayList();
    /**
     * All root items in the tree.
     */
    private final ObservableList<IFileReference> roots = FXCollections.observableArrayList();

    private final Map<String, T> fileMapByFilePath = new HashMap<>();

    private LocationContext fileExplorerLocation;

    /**
     * Register a node with {@link #items}.
     * If a node is an instance of {@link IFileReference} then the node is registered with {@link #files} in addition to {@link #items}
     * @param node the node to register
     */
    public void add(T node) {
        String uniqueIdentifier = node.getUniqueIdentifier();
        //only add if it's not there already
        //we don't want to trigger possible infinite recursion in the event that there is a listener adding these
        if (fileMapByFilePath.get(uniqueIdentifier) == null) {
            fileMapByFilePath.put(uniqueIdentifier, node);
            items.add(node);
            if (node instanceof IFileReference) {
                files.add((IFileReference) node);
            }
        }
    }

    /**
     * Remove a node from {@link #items}
     * @param identifier the identifier to look up the node by
     * @return the removed node
     */
    public T remove(String identifier) {
        T item =  fileMapByFilePath.remove(identifier);
        items.remove(item);
        if (item instanceof IFileReference) {
            files.remove(item);
            roots.remove(item);
        }
        return item;
    }

    /**
     * Remove a node from {@link #items}
     * @param node the node to remove
     * @return the removed node
     */
    public T remove(T node) {
        return remove(node.getUniqueIdentifier());
    }

    /**
     * Register a node with {@link #roots}.
     * Also registers the node with {@link #items} and {@link #files}.
     *
     * Use this method when adding root items to the tree as the {@link com.legyver.fenxlib.widgets.filetree.BaseFileExplorer} listens on these values to set
     * filesystem watches
     * @param node the node to register
     */
    public void addToRoot(T node) {
        add(node);
        if (node instanceof IFileReference && !roots.contains(node)) {
            roots.add((IFileReference) node);
        }
    }

    /**
     * Get the items in the tree
     * @return the items
     */
    public ObservableList<T> getItems() {
        return items;
    }

    /**
     * Get the subset of items that are files
     * @return the files
     */
    public ObservableList<IFileReference> getFiles() {
        return files;
    }

    /**
     * Get the root directories that have been added to the tree
     * @return the root directories
     */
    public ObservableList<IFileReference> getRoots() {
        return roots;
    }

    /**
     * Set the FileExplorer location
     * @param fileExplorerLocation the location context of the FileExplorer
     */
    public void setFileExplorerLocation(LocationContext fileExplorerLocation) {
        this.fileExplorerLocation = fileExplorerLocation;
    }

    /**
     * Get the FileExplorer location.
     * This is used to retrieve the TreeView associated with the registry when processing events.
     *
     * Not the {@link com.legyver.fenxlib.widgets.filetree.factory.SimpleFileExplorerFactory} sets this automatically.
     * If not using the {@link com.legyver.fenxlib.widgets.filetree.factory.SimpleFileExplorerFactory}, then set it yourself
     * using {@link #setFileExplorerLocation(LocationContext)}
     * @return the file explorer location
     */
    public LocationContext getFileExplorerLocation() {
        return fileExplorerLocation;
    }
}
