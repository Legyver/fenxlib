package com.legyver.fenxlib.widgets.filetree;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.core.event.handlers.ShowContextMenuEventHandler;
import com.legyver.fenxlib.api.scene.controls.options.TreeViewOptions;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TreeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Skin for the {@link BaseFileExplorer}
 */
public class FileExplorerSkin extends SkinBase<BaseFileExplorer> {
    private static final Logger logger = LogManager.getLogger(FileExplorerSkin.class);

    private TreeView jfxTreeView;

    /**
     * Construct a skin for the given FileExplorer
     * @param fileExplorer the FileExplorer to skin.
     */
    @SuppressWarnings("unchecked")
    public FileExplorerSkin(BaseFileExplorer fileExplorer) {
        super(fileExplorer);
        try {
            jfxTreeView = ControlsFactory.make(TreeView.class, new TreeViewOptions()
                    .root(fileExplorer.getPseudoRoot())
            );
            jfxTreeView.setShowRoot(false);
            fileExplorer.refreshPulseProperty().addListener((observable, oldValue, newValue) -> jfxTreeView.refresh());
            jfxTreeView.setOnContextMenuRequested(new ShowContextMenuEventHandler(jfxTreeView, fileExplorer.getContextMenu()));
            getChildren().add(jfxTreeView);
        } catch (CoreException e) {
            logger.error("Error constructing tree", e);
        }
    }
}
