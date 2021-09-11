package com.legyver.fenxlib.widgets.filetree.scan;

import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handle filesystem DELETE events triggered when a watched folder or file is deleted.
 */
public class FileSystemDeleteEventHandler implements FileSystemEventHandler {
    private static final Logger logger = LogManager.getLogger(FileSystemDeleteEventHandler.class);

    /**
     * Handle the DELETE event
     * @param fileTreeRegistry the registry to register the file with in the event of a CREATE event.
     *                         TODO: Shouldn't on-delete this also remove them?
     * @param fileSystemEvent the event information
     */
    @Override
    public void handle(FileTreeRegistry fileTreeRegistry, FileSystemEvent fileSystemEvent) {
        IFileReference parentFileReference = fileSystemEvent.getParentFileReference();
        IFileReference removedChildFileReference = fileSystemEvent.getFileReference();
        logger.trace("Item removed on filesystem [{}].  Refreshing parent [{}]", removedChildFileReference.getUniqueIdentifier(), parentFileReference.getUniqueIdentifier());
        FileTreeItem parentFileTreeItem = parentFileReference.getTreeNode();
        parentFileTreeItem.refresh();
    }
}
