package com.legyver.fenxlib.widgets.filetree;

import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.INodeReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.scan.IFileWatchHandler;
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
     * @param fileTreeRegistry the registry all files under this will be registered with
     * @param fileWatchHandler the handler to handle any created/deleted/modified files
     * @param areaContextMenu the context menu to display on the explorer pane
     */
    public SimpleFileExplorer(FileTreeRegistry<FileReference> fileTreeRegistry, IFileWatchHandler fileWatchHandler,
                              ContextMenu areaContextMenu) {
        super(fileTreeRegistry, fileWatchHandler, areaContextMenu);
    }

}
