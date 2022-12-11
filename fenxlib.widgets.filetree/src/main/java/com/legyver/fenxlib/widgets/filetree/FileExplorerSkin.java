package com.legyver.fenxlib.widgets.filetree;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.TreeViewOptions;
import com.legyver.fenxlib.core.util.LocationContextOperator;
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
            LocationContext fileExplorerLocation = new LocationContextOperator(fileExplorer).getLocationContext();
            TreeViewOptions treeViewOptions = fileExplorer.getTreeViewOptions();
            if (treeViewOptions.getName() == null) {
                treeViewOptions.name(BaseFileExplorer.LOCATION_TREEVIEW);
            }
            treeView = ControlsFactory.make(TreeView.class, fileExplorerLocation, treeViewOptions);
            treeView.setShowRoot(false);
            fileExplorer.setTreeView(treeView);

            fileExplorer.refreshPulseProperty().addListener((observable, oldValue, newValue) -> treeView.refresh());
            getChildren().add(treeView);
        } catch (CoreException e) {
            logger.error("Error constructing tree", e);
        }
    }
}
