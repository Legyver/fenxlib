package com.legyver.fenxlib.widgets.filetree.nodes;

import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;

import java.io.File;

/**
 * A wrapper for a file discovered by the adding of a file to the tree.
 * Includes:
 * - The File/Directory added
 * - Any children of the file, if the file is a directory.
 */
public class FileReference implements IFileReference {
    /**
     * The file to reference
     */
    private final File file;
    /**
     * The tree node representing the file
     */
    private FileTreeItem treeNode;

    /**
     * Construct a reference that wraps a file.  This will then be associated with a tree node in the filesystem tree.
     * @param file the file to show in the tree.
     */
    public FileReference(File file) {
        this.file = file;
    }

    /**
     * Get the file
     * @return the file
     */
    @Override
    public File getFile() {
        return file;
    }

    /**
     * Get the file
     * @return the file
     */
    @Override
    public File getValue() {
        return getFile();
    }

    /**
     * Get the absolute file path
     * @return the absolute file path
     */
    @Override
    public String getUniqueIdentifier() {
        return getFile().getAbsolutePath();
    }

    /**
     * See if the file is a directory
     * @return true if the wrapped file is a directory, false otherwise
     */
    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }

    /**
     * Get the short name of the file
     * @return the file name
     */
    @Override
    public String getName() {
        return file.getName();
    }

    /**
     * Get the tree node associated with the file
     * @return the tree node
     */
    @Override
    public FileTreeItem getTreeNode() {
        return treeNode;
    }

    /**
     * Set the tree node associated with the file
     * @param treeNode the tree node to associate with the file
     */
    @Override
    public void setTreeNode(FileTreeItem treeNode) {
        this.treeNode = treeNode;
    }
}
