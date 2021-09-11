package com.legyver.fenxlib.widgets.filetree;

import com.jfoenix.controls.JFXTreeView;
import com.legyver.fenxlib.core.api.event.handlers.ShowContextMenuEventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SkinBase;

/**
 * Skin for the {@link BaseFileExplorer}
 */
public class FileExplorerSkin extends SkinBase<BaseFileExplorer> {
    private final JFXTreeView jfxTreeView;

    /**
     * Construct a skin for the given FileExplorer
     * @param fileExplorer the FileExplorer to skin.
     */
    @SuppressWarnings("unchecked")
    public FileExplorerSkin(BaseFileExplorer fileExplorer) {
        super(fileExplorer);
        jfxTreeView = new JFXTreeView(fileExplorer.getPseudoRoot());
        jfxTreeView.setShowRoot(false);
        fileExplorer.refreshPulseProperty().addListener((observable, oldValue, newValue) -> jfxTreeView.refresh());
        jfxTreeView.setOnContextMenuRequested(new ShowContextMenuEventHandler(jfxTreeView, fileExplorer.getContextMenu()));
        getChildren().add(jfxTreeView);
    }
}
