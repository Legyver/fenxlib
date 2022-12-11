package com.legyver.fenxlib.widgets.filetree.utils;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.widgets.filetree.BaseFileExplorer;
import javafx.scene.control.TreeView;

import java.util.Optional;

/**
 * Location utilities for finding items in relation to a file explorer location
 */
public class LocationUtils {

    /**
     * Find the tree view associated with a FileExplorer.
     * This is mostly for things like getting the selection model for the tree view
     * @param fileExplorerLocation the location the FileExplorer is registered at
     * @return the TreeView
     * @throws CoreException if there is an error with locating the item with the query
     */
    public static TreeView findTreeViewForFileExplorer(LocationContext fileExplorerLocation) throws CoreException {
        ComponentQuery componentQuery = new ComponentQuery.QueryBuilder()
                .fromLocation(fileExplorerLocation)
                .build();
        Optional<BaseFileExplorer> explorerQuery = componentQuery.execute();
        BaseFileExplorer explorerForFileRegistry = explorerQuery.get();
        return explorerForFileRegistry.getTreeView();
    }
}
