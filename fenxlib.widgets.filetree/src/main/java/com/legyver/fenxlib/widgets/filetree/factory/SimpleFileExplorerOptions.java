package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.scene.controls.options.ContextMenuOptions;
import com.legyver.fenxlib.api.scene.controls.options.TreeViewOptions;
import com.legyver.fenxlib.widgets.filetree.SimpleFileExplorer;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.scan.FileWatchHandler;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;

/**
 * Options for a file explorer specifying
 * - What to do when a file is deleted/added/modified ({@link FileWatchHandler}
 * - The registry to register files with ({@link FileTreeRegistry})
 * - The factory to use to produce a context menu on various nodes in the tree
 */
public class SimpleFileExplorerOptions extends BaseControlBuilder<SimpleFileExplorerOptions> implements StyleableControlOptions<SimpleFileExplorer> {

    /**
     * The handler that handles new/deleted/modified files
     */
    private FileWatchHandler fileWatchHandler;
    /**
     * The file registry to be used for this file explorer
     */
    private FileTreeRegistry<FileReference> fileTreeRegistry;
    /**
     * Factory to create the Context Menu to display when a blank area of the explorer is right-clicked
     */
    private ContextMenuOptions areaContextMenuOptions;

    private TreeViewOptions treeViewOptions = new TreeViewOptions();

    /**
     * Add a file watch handler
     * @param fileWatchHandler the handler to use
     * @return this builder
     */
    public SimpleFileExplorerOptions fileWatchHandler(FileWatchHandler fileWatchHandler) {
        this.fileWatchHandler = fileWatchHandler;
        return me();
    }

    /**
     * Add a file tree registry
     * @param fileTreeRegistry the registry to register files with
     * @return this builder
     */
    public SimpleFileExplorerOptions fileTreeRegistry(FileTreeRegistry<FileReference> fileTreeRegistry) {
        this.fileTreeRegistry = fileTreeRegistry;
        areaContextMenuOptions = new ContextMenuOptions().name("area");
        return me();
    }

    /**
     * Custom factory to produce a context menu in the tree
     * @param areaContextMenuFactory the factory to use to produce context menus
     * @return this builder
     */
    public SimpleFileExplorerOptions areaContextMenuFactory(ContextMenuOptions areaContextMenuFactory) {
        this.areaContextMenuOptions = areaContextMenuFactory;
        return me();
    }

    /**
     * Specify options for the {@link javafx.scene.control.TreeView}
     * @param treeViewOptions options for the tree view
     * @return this builder
     */
    public SimpleFileExplorerOptions treeViewOptions(TreeViewOptions treeViewOptions) {
        this.treeViewOptions = treeViewOptions;
        return me();
    }

    /**
     * Get the file watch handler
     * @return the file watch handler
     */
    public FileWatchHandler getFileWatchHandler() {
        return fileWatchHandler;
    }

    /**
     * Get the file tree registry
     * @return the file tree registry
     */
    public FileTreeRegistry<FileReference> getFileTreeRegistry() {
        return fileTreeRegistry;
    }

    /**
     * Get the factory to produce context menus
     * @return the factory
     */
    public ContextMenuOptions getAreaContextMenuOptions() {
        return areaContextMenuOptions;
    }

    /**
     * Get the {@link TreeViewOptions} options.
     * @return the TreeView options
     */
    public TreeViewOptions getTreeViewOptions() {
        return treeViewOptions;
    }
}
