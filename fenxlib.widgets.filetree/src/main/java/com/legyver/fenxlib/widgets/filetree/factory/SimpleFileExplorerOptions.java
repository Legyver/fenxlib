package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.menu.factory.ContextMenuFactory;
import com.legyver.fenxlib.widgets.filetree.SimpleFileExplorer;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.scan.FileWatchHandler;

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
    private ContextMenuFactory areaContextMenuFactory;

    public SimpleFileExplorerOptions fileWatchHandler(FileWatchHandler fileWatchHandler) {
        this.fileWatchHandler = fileWatchHandler;
        return me();
    }

    public SimpleFileExplorerOptions fileTreeRegistry(FileTreeRegistry<FileReference> fileTreeRegistry) {
        this.fileTreeRegistry = fileTreeRegistry;
        return me();
    }

    public SimpleFileExplorerOptions areaContextMenuFactory(ContextMenuFactory areaContextMenuFactory) {
        this.areaContextMenuFactory = areaContextMenuFactory;
        return me();
    }

    public FileWatchHandler getFileWatchHandler() {
        return fileWatchHandler;
    }

    public FileTreeRegistry<FileReference> getFileTreeRegistry() {
        return fileTreeRegistry;
    }

    public ContextMenuFactory getAreaContextMenuFactory() {
        return areaContextMenuFactory;
    }
}
