package com.legyver.fenxlib.widgets.filetree.scan;

import com.legyver.fenxlib.widgets.filetree.factory.*;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileFilter;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default {@link IFileWatchHandler}.
 * Customize using the appropriate builder.
 */
public class FileWatchHandler implements IFileWatchHandler {
    private static final Logger logger = LogManager.getLogger(FileWatchHandler.class);

    private final FileTreeRegistry fileTreeRegistry;
    private final Map<String, FileSystemEventHandler> handlerMap;

    /**
     * Use the {@link Builder} for constructing FileWatchHandlers
     * @param fileTreeRegistry the FileTree registry
     * @param handlerMap the map of handlers
     */
    private FileWatchHandler(FileTreeRegistry fileTreeRegistry, Map<String, FileSystemEventHandler> handlerMap) {
        this.fileTreeRegistry = fileTreeRegistry;
        this.handlerMap = handlerMap;
    }

    @Override
    public final void handle(FileSystemEvent fileSystemEvent) {
        handle(fileSystemEvent.getEvent().kind().name(), fileSystemEvent);
    }

    @Override
    public final void handle(String eventKindName, FileSystemEvent fileSystemEvent) {
        FileSystemEventHandler handler = handlerMap.get(eventKindName);
        if (handler == null) {
            logger.warn("No handler registered for filesystem event: {}", eventKindName);
        } else {
            logger.trace("Handling filesystem event: {}", eventKindName);
            handler.handle(fileTreeRegistry, fileSystemEvent);
        }
    }

    /**
     * Builder to create a FileWatchHandler
     */
    public static class Builder {
        private Map<String, FileSystemEventHandler> handlerMap = new HashMap<>();
        private TreeItemChildFactory childFactory;
        /**
         * Factory to create the Context Menu to display when an item in the tree is selected
         */
        private final FileTreeItemContextMenuFactory fileTreeItemContextMenuFactory;
        private List<FileFilter> fileFilters = new ArrayList<>();

        /**
         * Constructor for the builder.
         * Provides default values for
         * - ChildFactory.  See {@link TreeItemChildFactory}
         * - CREATE event handler. See {@link FileSystemCreateEventHandler}
         * - DELETE event handler. See {@link FileSystemDeleteEventHandler}
         * - OVERFLOW event handler. See {@link FileSystemOverflowEventHandler}
         */
        public Builder() {
            fileTreeItemContextMenuFactory = new DefaultFileTreeItemContextMenuFactory();

            childFactory(new TreeItemChildFactory(fileTreeItemContextMenuFactory));
            withCreateHandler(new FileSystemCreateEventHandler());
            withDeleteHandler(new FileSystemDeleteEventHandler());
            withOverflowHandler(new FileSystemOverflowEventHandler());
        }

        /**
         * Build the FileWatchHandler and bind to the provided file tree registree.
         * @param fileTreeRegistry the file tree registry
         * @return the built FileWatchHandler
         */
        public FileWatchHandler build(FileTreeRegistry fileTreeRegistry) {
            for (FileFilter fileFilter : fileFilters) {
                childFactory.addFileFilter(fileFilter);
            }
            fileTreeItemContextMenuFactory.setFileTreeRegistry(fileTreeRegistry);
            FileSystemCreateEventHandler createEventHandler = (FileSystemCreateEventHandler) handlerMap.get(FileWatcherEventConstants.ENTRY_CREATE_NAME);
            createEventHandler.setChildFactory(childFactory);
            return new FileWatchHandler(fileTreeRegistry, handlerMap);
        }

        /**
         * Add a factory for a context menu item to display of a tree node.
         * @param fileTreeItemContextMenuItemFactory the factory to use to construct the menu item
         * @deprecated Use {@link #childFactory(TreeItemChildFactory)} to specify any custom menu items
         * @return this builder
         */
        @Deprecated
        public final Builder contextMenuItemFactory(FileTreeItemContextMenuItemFactory fileTreeItemContextMenuItemFactory) {
            this.fileTreeItemContextMenuFactory.addMenuItemFactory(fileTreeItemContextMenuItemFactory);
            return this;
        }

        /**
         * Provide a child factory to produce child-nodes as files/directories (and extended entries) are added/discovered
         * @param childFactory factory to use to produce the child tree node
         * @return this builder
         */
        public final Builder childFactory(TreeItemChildFactory childFactory) {
            this.childFactory = childFactory;
            return this;
        }

        /**
         * Provide one-or-more file filters to filter the files to be shown in the tree under discovered/added folders
         * @param fileFilter the filefilter to add
         * @return this builder
         */
        public final Builder fileFilter(FileFilter fileFilter) {
            this.fileFilters.add(fileFilter);
            return this;
        }

        /**
         * Provide a handler to use for an event matching a specific filesystem event
         * @param eventKindName event kind name.  Must match the {@link WatchEvent.Kind#name()}
         * @param fileSystemEventHandler the handler to use for the event kind.
         * @return this builder
         */
        public final Builder with(String eventKindName, FileSystemEventHandler fileSystemEventHandler) {
            handlerMap.put(eventKindName, fileSystemEventHandler);
            return this;
        }

        /**
         * Provide a handler to use for CREATE events for filesystem watches
         * @param fileSystemEventHandler the handler to use for create events
         * @return this builder
         */
        public final Builder withCreateHandler(FileSystemEventHandler fileSystemEventHandler) {
            return with(FileWatcherEventConstants.ENTRY_CREATE_NAME, fileSystemEventHandler);
        }

        /**
         * Provide a handler for MODIFY events for filesystem watches
         * @param fileSystemEventHandler the handler to use for modify events
         * @return this builder
         */
        public final Builder withModifyHandler(FileSystemEventHandler fileSystemEventHandler) {
            return with(FileWatcherEventConstants.ENTRY_MODIFY_NAME, fileSystemEventHandler);
        }

        /**
         * Provide a handler for DELETE events for filesystem watches
         * @param fileSystemEventHandler the handler to use for delete events
         * @return this builder
         */
        public final Builder withDeleteHandler(FileSystemEventHandler fileSystemEventHandler) {
            return with(FileWatcherEventConstants.ENTRY_DELETE_NAME, fileSystemEventHandler);
        }

        /**
         * Provide a handler for OVERFLOW events for filesystem watches
         * @param fileSystemEventHandler the handler to use for overflow events
         * @return this builder
         */
        public final Builder withOverflowHandler(FileSystemEventHandler fileSystemEventHandler) {
            return with(FileWatcherEventConstants.OVERFLOW_NAME, fileSystemEventHandler);
        }
    }
}
