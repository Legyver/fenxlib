package com.legyver.fenxlib.widgets.filetree.nodes;

import java.io.File;

/**
 * Provide the file associated with a node in the tree
 */
public interface IFileReference extends INodeReference<File> {
    /**
     * Get the file associated with the tree node
     * @return the file associated with the tree node
     */
    File getFile();
}
