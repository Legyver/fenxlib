package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.api.scene.controls.options.TextFieldOptions;
import com.legyver.fenxlib.core.event.handlers.ShowContextMenuEventHandler;
import com.legyver.fenxlib.core.event.handlers.ShowContextMenuOnRightClick;
import com.legyver.fenxlib.widgets.filetree.BaseFileExplorer;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.INodeReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.tree.FileTreeItem;
import com.legyver.fenxlib.widgets.filetree.tree.TreeFile;
import com.legyver.fenxlib.widgets.filetree.tree.TreeFolder;
import com.legyver.fenxlib.widgets.filetree.tree.internal.TreeRoot;
import com.legyver.fenxlib.widgets.filetree.utils.LocationUtils;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Factory to create child nodes in the tree.
 * This is the main mechanism to customize and extend trees to non-filesystem things.
 */
public class TreeItemChildFactory {
    private static final Logger logger = LogManager.getLogger(TreeItemChildFactory.class);

    private final List<FileFilter> fileFilters = new ArrayList<>();
    /**
     * Factory to create the Context Menu to display when an item in the tree is selected
     */
    private final FileTreeItemContextMenuFactory fileTreeItemContextMenuFactory;

    private TreeView treeView = null;

    /**
     * Construct a factory to create a tree item child
     * @param fileTreeItemContextMenuFactory the factory to produce the child-specific context menu
     */
    public TreeItemChildFactory(FileTreeItemContextMenuFactory fileTreeItemContextMenuFactory) {
        this.fileTreeItemContextMenuFactory = fileTreeItemContextMenuFactory;
    }

    /**
     * Add a file filter to filter the files to be shown in the tree.  If no filters are provided, all files are shown.
     * Directories are always shown.
     * @param fileFilter the filter to use on {@link File#listFiles(FileFilter)}
     */
    public void addFileFilter(FileFilter fileFilter) {
        this.fileFilters.add(fileFilter);
    }

    /**
     * Make the node for the node reference.
     * If the node references a file (instanceof {@link com.legyver.fenxlib.widgets.filetree.nodes.IFileReference}) then an entry is added for it and any children.
     * In other cases, delegates to the template method {@link #makeNonFileNode(FileTreeItem, INodeReference)}
     * @param fileTreeRegistry the registry to add the file to
     * @param parentFileTreeItem the parent node
     * @param nodeReference the node reference to add
     * @return the new FileTreeNode
     */
    @SuppressWarnings("unchecked")
    public FileTreeItem makeNode(FileTreeRegistry fileTreeRegistry, FileTreeItem parentFileTreeItem, INodeReference nodeReference) {
        if (fileTreeRegistry != null) {
            this.fileTreeItemContextMenuFactory.setFileTreeRegistry(fileTreeRegistry);
            if (treeView == null) {
                try {
                    LocationContext fileExplorerLocation = fileTreeRegistry.getFileExplorerLocation();
                    treeView = LocationUtils.findTreeViewForFileExplorer(fileExplorerLocation);
                } catch (CoreException coreException) {
                    logger.error(coreException);
                }
            }
        }
        FileTreeItem treeItem;
        if (nodeReference instanceof FileReference) {
            File file = ((FileReference) nodeReference).getFile();
            fileTreeRegistry.add(nodeReference);
            if (file.isDirectory()) {
                treeItem = makeTreeFolder(parentFileTreeItem, (FileReference) nodeReference);
                if (fileFilters.isEmpty()) {
                    //add all nodes
                    for (File childFile : file.listFiles()) {
                        addFileNode(fileTreeRegistry, treeItem, childFile);
                    }
                } else {
                    //add all directories
                    for (File subDir : file.listFiles((FileFilter) DirectoryFileFilter.INSTANCE)) {
                        addFileNode(fileTreeRegistry, treeItem, subDir);
                    }
                    //then add all files
                    for (FileFilter fileFilter : fileFilters) {
                        for (File child : file.listFiles(fileFilter)) {
                            addFileNode(fileTreeRegistry, treeItem, child);
                        }
                    }
                }
            } else {
                treeItem = makeTreeFile(parentFileTreeItem, (FileReference) nodeReference);
            }
        } else {
            treeItem = makeNonFileNode(parentFileTreeItem, nodeReference);
        }

        initContextMenu(treeItem);

        return treeItem;
    }

    @SuppressWarnings("unchecked")
    private void initContextMenu(FileTreeItem treeItem) {
        ContextMenu contextMenu = fileTreeItemContextMenuFactory.makeContextMenu(treeItem);
        treeItem.getGraphic().setOnContextMenuRequested(new ShowContextMenuEventHandler(treeItem.getGraphic(), contextMenu));
        treeItem.addEventHandler(MouseEvent.MOUSE_CLICKED, new ShowContextMenuOnRightClick(treeItem.getGraphic(), contextMenu));
    }

    private void addFileNode(FileTreeRegistry fileTreeRegistry, FileTreeItem parentTreeItem, File file) {
        FileReference childReference = new FileReference(file);
        FileTreeItem childTreeItem = makeNode(fileTreeRegistry, parentTreeItem, childReference);
        parentTreeItem.addChild(childTreeItem);
    }


    /**
     * Make a file-tree node that does not correspond to an actual file.
     * This is ended to allow for display of things within files, withing the file tree
     * @param parentFileTreeItem the parent node in the tree
     * @param nodeReference the reference to be added to the tree
     * @return the new tree item
     */
    protected FileTreeItem makeNonFileNode(FileTreeItem parentFileTreeItem, INodeReference nodeReference) {
        return null;
    }

    /**
     * Instantiate a tree folder
     * @param parentFileTreeItem the parent node in the tree
     * @param fileReference the folder the tree item will represent
     * @return the new tree folder
     */
    protected TreeFolder makeTreeFolder(FileTreeItem parentFileTreeItem, FileReference fileReference) {
        TreeFolder treeFolder = new TreeFolder(treeView, fileReference);
        initializeContextMenuItemEnabledProperties(parentFileTreeItem, treeFolder);
        return treeFolder;
    }

    /**
     * Instantiate a tree file
     *
     * @param parentFileTreeItem the parent node in the tree
     * @param fileReference      the file the tree item will represent
     * @return the new tree file
     */
    protected TreeFile makeTreeFile(FileTreeItem parentFileTreeItem, FileReference fileReference) {
        TreeFile treeFile = new TreeFile(treeView, fileReference);
        initializeContextMenuItemEnabledProperties(parentFileTreeItem, treeFile);
        return treeFile;
    }

    /**
     * Initialize the context menu enabled properties.
     * Use this method to turn on/off context menu items
     * By default,the {@link FileTreeRemoveEventHandlerFactory#MENU_ITEM_NAME} if this node's parent is an instance of {@link TreeRoot}.
     * @param parentFileTreeItem the parent node in the tree
     * @param treeFile the tree file the context menu options apply to
     */
    protected void initializeContextMenuItemEnabledProperties(FileTreeItem parentFileTreeItem, TreeFile treeFile) {
        if (parentFileTreeItem instanceof TreeRoot) {
            treeFile.addMenuItemEnabledProperty(FileTreeRemoveEventHandlerFactory.MENU_ITEM_NAME, new SimpleBooleanProperty(true));
        }

    }

    /**
     * Initialize the context menu enabled properties.
     * Use this method to turn on/off context menu items.
     * By default,the {@link FileTreeRemoveEventHandlerFactory#MENU_ITEM_NAME} if this node's parent is an instance of {@link TreeRoot}
     * @param parentFileTreeItem the parent node in the tree
     * @param treeFolder the tree folder the context menu options apply to
     */
    protected void initializeContextMenuItemEnabledProperties(FileTreeItem parentFileTreeItem, TreeFolder treeFolder) {
        if (parentFileTreeItem instanceof TreeRoot) {
            treeFolder.addMenuItemEnabledProperty(FileTreeRemoveEventHandlerFactory.MENU_ITEM_NAME, new SimpleBooleanProperty(true));
        }
    }

}
