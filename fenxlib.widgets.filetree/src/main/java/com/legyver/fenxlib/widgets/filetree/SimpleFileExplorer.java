package com.legyver.fenxlib.widgets.filetree;

import com.legyver.fenxlib.widgets.filetree.factory.SimpleFileExplorerOptions;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.INodeReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import javafx.scene.control.ContextMenu;

/**
 * A simple file explorer that just shows the nodes in the tree.
 * Root nodes can be:
 *  - removed by a context menu
 *  - added by a context menu
 * Once a file/directory is added to the file registry (using {@link FileTreeRegistry#addToRoot(INodeReference)})
 *  the file/directory will automatically be scanned for child files/directories.
 *  All added and found files/directories will be watched so that any changes on the file system automatically show in the tree.
 *
 */
public class SimpleFileExplorer extends BaseFileExplorer<FileReference, FileTreeRegistry<FileReference>> {

    /**
     * Construct a SimpleFileExplorer
     * @param simpleFileExplorerOptions options for constructing the FileExplorer
     * @param areaContextMenu the context menu to display on the explorer pane
     */
    public SimpleFileExplorer(SimpleFileExplorerOptions simpleFileExplorerOptions,
                              ContextMenu areaContextMenu) {
        super(simpleFileExplorerOptions.getFileTreeRegistry(), simpleFileExplorerOptions.getFileWatchHandler(), simpleFileExplorerOptions.getTreeViewOptions(), areaContextMenu);
    }

}
