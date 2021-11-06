package com.legyver.fenxlib.widgets.filetree.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default FileTreeItemContextMenuFactory
 * Decorates any passed in factories with the default FileTreeRemoveEventHandlerFactory
 */
public class DefaultFileTreeItemContextMenuFactory extends FileTreeItemContextMenuFactory {

    /**
     * Construct a FileTreeItemContextMenuFactory with the menu items produced by the passed in factories.
     * Adds the default FileTreeRemoveEventHandlerFactory to the list
     * @param fileTreeItemContextMenuItemFactories factories producing the tree item menu items
     */
    public DefaultFileTreeItemContextMenuFactory(FileTreeItemContextMenuItemFactory...fileTreeItemContextMenuItemFactories) {
        super(decorateVarArgs(new FileTreeItemContextMenuItemFactory(FileTreeRemoveEventHandlerFactory.MENU_ITEM_NAME, new FileTreeRemoveEventHandlerFactory()),
                fileTreeItemContextMenuItemFactories)
        );
    }

    private static FileTreeItemContextMenuItemFactory[] decorateVarArgs(FileTreeItemContextMenuItemFactory removeMenuItem, FileTreeItemContextMenuItemFactory[] fileTreeItemContextMenuItemFactories) {
        List<FileTreeItemContextMenuItemFactory> factoryList = new ArrayList<>();
        factoryList.add(removeMenuItem);
        if (fileTreeItemContextMenuItemFactories != null) {
            for (FileTreeItemContextMenuItemFactory factory : fileTreeItemContextMenuItemFactories) {
                factoryList.add(factory);
            }
        }
        Collections.sort(factoryList);
       return factoryList.toArray(new FileTreeItemContextMenuItemFactory[factoryList.size()]);
    }
}
