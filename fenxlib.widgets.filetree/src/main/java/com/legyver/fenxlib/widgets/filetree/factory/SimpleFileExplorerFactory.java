package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.factory.menu.ContextMenuFactory;
import com.legyver.fenxlib.core.impl.factory.menu.file.ImportDirectoryDecorator;
import com.legyver.fenxlib.core.impl.factory.menu.file.SelectDirectoryMenuFactory;
import com.legyver.fenxlib.widgets.filetree.SimpleFileExplorer;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.scan.FileWatchHandler;
import javafx.scene.control.ContextMenu;

/**
 * Factory to create a {@link SimpleFileExplorer}
 */
public class SimpleFileExplorerFactory implements NodeFactory<SimpleFileExplorer> {
    /**
     * The default file explorer name to register as.
     */
    public static final String DEFAULT_EXPLORER_NAME = "fileExplorer";
    /**
     * The name to register the file explorer as.  This is used for querying the file explorer from the {@link com.legyver.fenxlib.core.api.locator.IComponentRegistry}
     */
    private final String name;
    /**
     * The handler that handles new/deleted/modified files
     */
    private final FileWatchHandler fileWatchHandler;
    /**
     * The file registry to be used for this file explorer
     */
    private final FileTreeRegistry<FileReference> fileTreeRegistry;
    /**
     * Factory to create the Context Menu to display when a blank area of the explorer is right-clicked
     */
    private final ContextMenuFactory areaContextMenuFactory;


    /**
     * Construct a FileExplorerFactory
     * @param name the name to register the file explorer as with the {@link com.legyver.fenxlib.core.api.locator.IComponentRegistry}
     * @param fileTreeRegistry the registry that stores a record of files this file explorer will be responsible for
     * @param fileWatchHandler the handler that handles any new/deleted/modified files that are added or discovered under watched directories
     * @param areaContextMenuFactory the factory to produce the context menu for the file explorer
     */
    public SimpleFileExplorerFactory(String name, FileTreeRegistry<FileReference> fileTreeRegistry, FileWatchHandler fileWatchHandler,
                                     ContextMenuFactory areaContextMenuFactory) {
        this.name = name;
        this.fileWatchHandler = fileWatchHandler;
        this.fileTreeRegistry = fileTreeRegistry;
        this.areaContextMenuFactory = areaContextMenuFactory;
    }


    /**
     * Construct a FileExplorerFactory.  The name is defaulted to the {@link #DEFAULT_EXPLORER_NAME}.
     * @param fileTreeRegistry the registry that stores a record of files this file explorer will be responsible for
     * @param fileWatchHandler the handler that handles any new/deleted/modified files that are added or discovered under watched directories
     * @throws CoreException if there is a problem reading the default file system location to browse to
     */
    public SimpleFileExplorerFactory(FileTreeRegistry<FileReference> fileTreeRegistry, FileWatchHandler fileWatchHandler) throws CoreException {
        this(DEFAULT_EXPLORER_NAME, fileTreeRegistry, fileWatchHandler,
                new ContextMenuFactory("area", new ImportDirectoryDecorator("Add directory", "Select directory", new SelectDirectoryMenuFactory(), fileOptions -> {
                    fileTreeRegistry.addToRoot(new FileReference(fileOptions.getFile()));
                }))
        );
    }

    /**
     * Make the SimpleFileExplorer and register it as the {@link #name} under the parent item.
     * @param locationContext the (parent) location context to register the widget under
     * @return the new FileExplorer
     */
    @Override
    public SimpleFileExplorer makeNode(LocationContext locationContext) throws CoreException {
        ContextMenu areaContextMenu = areaContextMenuFactory.makeMenu(locationContext);
        LocationContext decoratedLocationContext = new LocationContextDecorator(locationContext);
        decoratedLocationContext.setName(name);

        SimpleFileExplorer simpleFileExplorer = new SimpleFileExplorer(fileTreeRegistry, fileWatchHandler, areaContextMenu);
        ApplicationContext.getComponentRegistry().register(decoratedLocationContext, simpleFileExplorer);
        return simpleFileExplorer;
    }
}
