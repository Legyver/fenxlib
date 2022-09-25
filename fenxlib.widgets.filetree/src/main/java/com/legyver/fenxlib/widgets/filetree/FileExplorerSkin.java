package com.legyver.fenxlib.widgets.filetree;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
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

    private TreeView treeView;

    /**
     * Construct a skin for the given FileExplorer
     * @param fileExplorer the FileExplorer to skin.
     */
    public FileExplorerSkin(BaseFileExplorer fileExplorer) {
        super(fileExplorer);
        try {
            treeView = ControlsFactory.make(TreeView.class, new TreeViewOptions()
                    .root(fileExplorer.getPseudoRoot())
            );
            treeView.setShowRoot(false);
            fileExplorer.refreshPulseProperty().addListener((observable, oldValue, newValue) -> treeView.refresh());
            getChildren().add(treeView);
        } catch (CoreException e) {
            logger.error("Error constructing tree", e);
        }
    }
}
