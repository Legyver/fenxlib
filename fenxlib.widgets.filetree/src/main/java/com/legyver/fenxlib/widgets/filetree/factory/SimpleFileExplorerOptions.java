package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.files.action.OpenDirectoryAction;
import com.legyver.fenxlib.core.menu.factory.ContextMenuFactory;
import com.legyver.fenxlib.api.menu.factory.IMenuItemFactory;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import com.legyver.fenxlib.widgets.filetree.SimpleFileExplorer;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.scan.FileWatchHandler;
import javafx.scene.control.MenuItem;

/**
 * Options for a file explorer specifying
 * - What to do when a file is deleted/added/modified ({@link FileWatchHandler}
 * - The registry to register files with ({@link FileTreeRegistry})
 * - The factory to use to produce a context menu on various nodes in the tree
 */
public class SimpleFileExplorerOptions extends BaseControlBuilder<SimpleFileExplorerOptions> implements StyleableControlOptions<SimpleFileExplorer> {
    /**
     * The handler that handles new/deleted/modified files
     */
    private FileWatchHandler fileWatchHandler;
    /**
     * The file registry to be used for this file explorer
     */
    private FileTreeRegistry<FileReference> fileTreeRegistry;
    /**
     * Factory to create the Context Menu to display when a blank area of the explorer is right-clicked
     */
    private ContextMenuFactory areaContextMenuFactory;

    /**
     * Construct options for the file explorer with default context menu factory
     * @throws CoreException if there is an error
     */
    public SimpleFileExplorerOptions() throws CoreException {
        MenuItemOptions addDirectoryOptions = new MenuItemOptions()
                .text("legyver.defaults.label.filetree.canvas.menu.add.directory")
                .eventHandler(new OpenDirectoryAction("legyver.defaults.label.filetree.canvas.browser.select.directory", fileOptions -> {
                        fileTreeRegistry.addToRoot(new FileReference(fileOptions.getFile()));
                    })
                );
        areaContextMenuFactory = new ContextMenuFactory("area", new CachingMenuItemFactory(addDirectoryOptions));
    }

    /**
     * Add a file watch handler
     * @param fileWatchHandler the handler to use
     * @return this builder
     */
    public SimpleFileExplorerOptions fileWatchHandler(FileWatchHandler fileWatchHandler) {
        this.fileWatchHandler = fileWatchHandler;
        return me();
    }

    /**
     * Add a file tree registry
     * @param fileTreeRegistry the registry to register files with
     * @return this builder
     */
    public SimpleFileExplorerOptions fileTreeRegistry(FileTreeRegistry<FileReference> fileTreeRegistry) {
        this.fileTreeRegistry = fileTreeRegistry;
        return me();
    }

    /**
     * Custom factory to produce a context menu in the tree
     * @param areaContextMenuFactory the factory to use to produce context menus
     * @return this builder
     */
    public SimpleFileExplorerOptions areaContextMenuFactory(ContextMenuFactory areaContextMenuFactory) {
        this.areaContextMenuFactory = areaContextMenuFactory;
        return me();
    }

    /**
     * Get the file watch handler
     * @return the file watch handler
     */
    public FileWatchHandler getFileWatchHandler() {
        return fileWatchHandler;
    }

    /**
     * Get the file tree registry
     * @return the file tree registry
     */
    public FileTreeRegistry<FileReference> getFileTreeRegistry() {
        return fileTreeRegistry;
    }

    /**
     * Get the factory to produce context menus
     * @return the factory
     */
    public ContextMenuFactory getAreaContextMenuFactory() {
        return areaContextMenuFactory;
    }

    private static class CachingMenuItemFactory implements IMenuItemFactory<MenuItem, MenuItemOptions> {
        private final MenuItemOptions staticOptions;

        private CachingMenuItemFactory(MenuItemOptions staticOptions) {
            this.staticOptions = staticOptions;
        }

        @Override
        public MenuItem makeNode(LocationContext locationContext, MenuItemOptions options) throws CoreException {
            return ControlsFactory.make(MenuItem.class, locationContext, staticOptions);
        }

        @Override
        public MenuItemOptions newOptions() {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }
}
