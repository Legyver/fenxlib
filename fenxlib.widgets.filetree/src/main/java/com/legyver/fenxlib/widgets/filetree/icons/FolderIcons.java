package com.legyver.fenxlib.widgets.filetree.icons;

/**
 * Icons to use for filesystem folders
 */
public enum FolderIcons {
    /**
     * Folder icon
     */
    ICON_FOLDER("icon-folder"),
    /**
     * Open folder icon
     */
    ICON_FOLDER_OPEN("icon-folder-open");

    private final String iconName;

    FolderIcons(String iconName) {
        this.iconName = iconName;
    }

    /**
     * Get the icon name
     * @return the icon name
     */
    public String getIconName() {
        return iconName;
    }
}
