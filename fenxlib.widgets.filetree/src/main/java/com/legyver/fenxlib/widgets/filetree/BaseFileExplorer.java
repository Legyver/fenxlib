package com.legyver.fenxlib.widgets.filetree;

import com.legyver.fenxlib.api.config.ConfigServiceRegistry;
import com.legyver.fenxlib.api.config.IApplicationConfig;
import com.legyver.fenxlib.api.config.parts.RecentFile;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.DefaultFileOptions;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.locator.IComponentRegistry;
import com.legyver.fenxlib.api.scene.controls.options.TreeViewOptions;
import com.legyver.fenxlib.core.util.GuidUtil;
import com.legyver.fenxlib.extensions.tuktukfx.task.adapter.JavaFxAdapter;
import com.legyver.fenxlib.extensions.tuktukfx.task.exec.TaskExecutor;
import com.legyver.fenxlib.widgets.filetree.config.FileTreeConfigAware;
import com.legyver.fenxlib.widgets.filetree.config.FileTreeConfigSection;
import com.legyver.fenxlib.widgets.filetree.config.WorkingFileSet;
import com.legyver.fenxlib.widgets.filetree.event.ImportDirectoryConsumer;
import com.legyver.fenxlib.widgets.filetree.nodes.FileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.IFileReference;
import com.legyver.fenxlib.widgets.filetree.nodes.INodeReference;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import com.legyver.fenxlib.widgets.filetree.scan.FileSystemEvent;
import com.legyver.fenxlib.widgets.filetree.scan.FileWatcherEventConstants;
import com.legyver.fenxlib.widgets.filetree.scan.IFileWatchHandler;
import com.legyver.fenxlib.widgets.filetree.task.FileSystemWatchTaskContext;
import com.legyver.fenxlib.widgets.filetree.task.FileSystemWatchTaskFactory;
import com.legyver.fenxlib.widgets.filetree.tree.internal.TreeRoot;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.stream.Collectors;

/**
 * Base class for FileExplorer widgets.
 * Known concrete implementations: See {@link SimpleFileExplorer}.
 *
 * @param <T> type of the nodes associated with the explorer
 * @param <U> type of the registry associated with the file explorer instance
 */
public abstract class BaseFileExplorer<T extends INodeReference, U extends FileTreeRegistry<T>> extends Control {
    /**
     * The name of the tree view if not otherwise specified in the {@link #treeViewOptions}
     */
    public static final String LOCATION_TREEVIEW = "_TREEVIEW_";
    private static final Logger logger = LogManager.getLogger(BaseFileExplorer.class);
    /**
     * The root node in the tree all root folders will be children of
     */
    private final TreeRoot pseudoRoot;
    private final TreeViewOptions treeViewOptions;

    //the tree view rendering the tree (set from the FileExplorerSkin)
    private TreeView treeView;

    /**
     * A special case reference for the root node's association.
     * This is essentially to simplify how tree icons are associated with an {@link INodeReference}
     */
    private final FileReference pseudoRootFileReference;
    /**
     * Refresh pulse that can be monitored for when a node changes state
     */
    private final BooleanProperty refreshPulse = new SimpleBooleanProperty(false);

    /**
     * Construct a BaseFileExplorer with the supplied file registry and fileWatch handler.
     * This constructor
     * - Registers the root tree node with the {@link IComponentRegistry} so it can be queried from anywhere in the application.
     * -- format will be the registered name of the file explorer + "_root"
     * - Adds a listener to the root files of the registry associated with this file explorer.
     * --  use the {@link FileTreeRegistry#addToRoot(INodeReference)} method when adding root-level folders.
     * - Creates a task to watch the filesystem and sync all changes in files/folders represented in this tree.
     *
     * @param fileTreeRegistry the registry that all nodes associated with the explorer tree will be registered with
     * @param fileWatchHandler the handler that is notified whenever the node associated with a tree item is removed/added/renamed on the filesystem
     * @param treeViewOptions the options for constructing the {@link TreeView}
     * @param areaContextMenu  the context menu displayed when clicking on blank area of explorer
     */
    protected BaseFileExplorer(U fileTreeRegistry, IFileWatchHandler fileWatchHandler, TreeViewOptions treeViewOptions, ContextMenu areaContextMenu) {
        this.pseudoRootFileReference = new PseudoReference() {
            @Override
            public String getUniqueIdentifier() {
                String guid =  getExplorerGuid() + "_root";//unique to explorer instance
                GuidUtil.setGuid(pseudoRoot, guid);
                return guid;
            }
        };
        this.pseudoRoot = new TreeRoot(pseudoRootFileReference);
        this.treeViewOptions = treeViewOptions;
        treeViewOptions.root(pseudoRoot);

        FileSystemWatchTaskContext context = new FileSystemWatchTaskContext(fileTreeRegistry.getFiles(), fileWatchHandler);

        fileTreeRegistry.getRoots().addListener(new ListChangeListener<IFileReference>() {
            @Override
            public void onChanged(Change<? extends IFileReference> c) {
                logger.trace("Refreshing explorer nodes");
                c.next();
                if (c.wasAdded()) {
                    for (IFileReference node : c.getAddedSubList()) {
                        logger.trace("Refreshing node: {}", node.getUniqueIdentifier());
                        //reuse the new file functionality to handle layout in tree consistently
                        fileWatchHandler.handle(FileWatcherEventConstants.ENTRY_CREATE_NAME, new FileSystemEvent(pseudoRootFileReference, node, null));
                    }
                } else if (c.wasRemoved()) {
                    for (IFileReference node : c.getRemoved()) {
                        //don't need to do more than log it, because removal happens when a file/directory is deleted on the filesystem
                        //therefore removal has already happened (deleted on filesystem calls a FileSystemEvent for delete, which then in turn removes it from the list)
                        logger.trace("Removing node: {}", node.getUniqueIdentifier());
                    }
                }
                Platform.runLater(() -> {
                    if (logger.isTraceEnabled()) {
                        @SuppressWarnings("unchecked")
                        ObservableList<TreeItem> children = pseudoRoot.getChildren();
                        String topLevel = children.stream()
                                .map(treeItem -> treeItem.getValue())
                                .map(Object::toString)
                                .collect(Collectors.joining("\n"));
                        logger.trace("Root children: {}", topLevel);
                    }

                    pseudoRoot.setExpanded(true);
                    refreshPulse.set(!refreshPulse.get());
                });
            }
        });

        setContextMenu(areaContextMenu);
        JavaFxAdapter adapter = FileSystemWatchTaskFactory.createScanTask(context);
        //since we're using a thread pool to monitor the filesystem, shut down the thread pool on application exit
        //we use a delay matching the tryAcquire timeout in FileSystemWatchTaskProcessor#runUntilAbort() so we don't get an interrupted exception in the latter
        TaskExecutor.getInstance().configure().delayShutdown(2000);
        TaskExecutor.getInstance().submitTask(adapter);

        reloadTreeAndSetupConfigSync(fileTreeRegistry);
    }

    @SuppressWarnings("unchecked")
    private void reloadTreeAndSetupConfigSync(U fileTreeRegistry) {
        IApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
        if (applicationConfig instanceof FileTreeConfigAware) {
            FileTreeConfigAware fileTreeConfigAware = (FileTreeConfigAware) applicationConfig;
            FileTreeConfigSection fileTreeConfigSection = fileTreeConfigAware.getFileTreeConfig();
            if (fileTreeConfigSection == null) {
                fileTreeConfigSection = new FileTreeConfigSection();
                fileTreeConfigAware.setFileTreeConfig(fileTreeConfigSection);
            }
            WorkingFileSet workingFileSet = fileTreeConfigSection.getWorkingFileSet();

            //we don't have the FileTree until the FileExplorer is skinned
            Platform.runLater(new InitialTreeLoader(fileTreeRegistry, workingFileSet));

            ApplicationContext.getApplicationLifecycleHookRegistry().registerHook(LifecyclePhase.PRE_SHUTDOWN, () -> {
                workingFileSet.getValues().clear();
                for (IFileReference fileReference : fileTreeRegistry.getRoots()) {
                    RecentFile recentlyViewedFile = ConfigServiceRegistry.getInstance().adaptRecentlyViewedFile(fileReference.getFile());
                    workingFileSet.getValues().add(recentlyViewedFile);
                }
            }, -10);
        }
    }

    private String getExplorerGuid() {
        return GuidUtil.getGuid(this);
    }

    /**
     * Get the tree view
     * @return the tree view
     */
    public TreeView getTreeView() {
        return treeView;
    }

    //below methods package-private because it should only be used by the Skin
    BooleanProperty refreshPulseProperty() {
        return refreshPulse;
    }

    void setTreeView(TreeView treeView) {
        this.treeView = treeView;
    }

    TreeViewOptions getTreeViewOptions() {
        return treeViewOptions;
    }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new FileExplorerSkin(this);
    }

    private static class InitialTreeLoader implements Runnable {
        private final FileTreeRegistry fileTreeRegistry;
        private final WorkingFileSet workingFileSet;

        private InitialTreeLoader(FileTreeRegistry fileTreeRegistry, WorkingFileSet workingFileSet) {
            this.fileTreeRegistry = fileTreeRegistry;
            this.workingFileSet = workingFileSet;
        }

        @Override
        public void run() {
            if (fileTreeRegistry.getFileExplorerLocation() == null) {
                Platform.runLater(this);
            } else {
                @SuppressWarnings("unchecked")
                ImportDirectoryConsumer importDirectoryConsumer = new ImportDirectoryConsumer((FileTreeRegistry<IFileReference>) fileTreeRegistry);
                for (RecentFile fileReference : workingFileSet.getValues()) {
                    File file = new File(fileReference.getPath());
                    if (file.exists()) {
                        importDirectoryConsumer.accept(new DefaultFileOptions(file, false));
                    }
                }
            }
        }
    }

    private static class PseudoReference extends FileReference {

        private PseudoReference() {
            super(null);
        }

        @Override
        public boolean isDirectory() {
            return true;
        }

        @Override
        public String getName() {
            return null;
        }
    }
}
