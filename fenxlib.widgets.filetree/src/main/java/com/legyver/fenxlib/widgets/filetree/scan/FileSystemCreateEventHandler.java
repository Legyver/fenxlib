package com.legyver.fenxlib.widgets.filetree.scan;

import com.legyver.fenxlib.widgets.filetree.factory.FileTreeRemoveEventHandlerFactory;
import com.legyver.fenxlib.widgets.filetree.factory.TreeItemChildFactory;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import com.legyver.fenxlib.widgets.filetree.tree.internal.TreeRoot;
import javafx.application.Platform;
import javafx.scene.control.TreeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handle filesystem CREATE events triggered when a watched folder has a directory or file created under it.
 */
public class FileSystemCreateEventHandler implements FileSystemEventHandler {
    private static final Logger logger = LogManager.getLogger(FileSystemCreateEventHandler.class);

    private TreeItemChildFactory childFactory;

    /**
     * Handle CREATE events.
     * If the parent file tree item is the tree root, the {@link FileTreeRemoveEventHandlerFactory#MENU_ITEM_NAME} is set to true.
     * @param fileTreeRegistry the registry to register the file with in the event of a CREATE event
     * @param fileSystemEvent the event information
     */
    @Override
    public void handle(FileTreeRegistry fileTreeRegistry, FileSystemEvent fileSystemEvent) {
        IFileReference parentFileReference = fileSystemEvent.getParentFileReference();
        FileTreeItem parentFileTreeItem = parentFileReference.getTreeNode();
        IFileReference createdChildFileReference = fileSystemEvent.getFileReference();
        FileTreeItem fileTreeItem = childFactory.makeNode(fileTreeRegistry, parentFileTreeItem, createdChildFileReference);
        parentFileTreeItem.addChild(fileTreeItem);
        parentFileTreeItem.refresh();

        if (!fileTreeItem.isExpanded() && parentFileTreeItem instanceof TreeRoot) {
            Platform.runLater(
                    () -> {
                        logger.debug("Auto expanding added node: {}", fileTreeItem.getName());
                        fileTreeItem.setExpanded(true);
                    }
            );
        }
    }

    /**
     * Set the ChildFactory on the CREATE handler.  This is used to create the appropriate node in the tree when a file is added/discovered.
     * @param childFactory the factory to use
     */
    public void setChildFactory(TreeItemChildFactory childFactory) {
        this.childFactory = childFactory;
    }
}
