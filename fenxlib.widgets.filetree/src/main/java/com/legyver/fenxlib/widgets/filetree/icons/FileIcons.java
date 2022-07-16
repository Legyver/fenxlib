package com.legyver.fenxlib.widgets.filetree.icons;

import com.legyver.fenxlib.api.icons.application.IApplicationIcon;

/**
 * Enumeration of icons for different types of files
 */
public enum FileIcons implements IApplicationIcon {
    //file types
    /**
     * The icon to use for PDF files
     */
    ICON_FILE_PDF("icon-file-pdf"),
    /**
     * The icon to use for OpenOffice files
     */
    ICON_FILE_OPENOFFICE("icon-file-openoffice"),
    /**
     * The icon to use for Word files
     */
    ICON_FILE_WORD("icon-file-word"),
    /**
     * The icon to use for Excel files
     */
    ICON_FILE_EXCEL("icon-file-excel"),
    /**
     * The icon to use for text files
     */
    ICON_FILE_TEXT("icon-file-text"),
    /**
     * The icon to use for empty files
     */
    ICON_FILE_EMPTY("icon-file-empty"),
    /**
     * The icon to use for empty files
     */
    ICON_FILES_EMPTY("icon-files-empty"),
    /**
     * The icon to use for picture files
     */
    ICON_FILE_PICTURE("icon-file-picture"),
    /**
     * the icon to use for music files
     */
    ICON_FILE_MUSIC("icon-file-music"),
    /**
     * The icon to use for playing a file
     */
    ICON_FILE_PLAY("icon-file-play"),
    /**
     * The icon to use for video files
     */
    ICON_FILE_VIDEO("icon-file-video"),
    /**
     * The icon to use for zip files
     */
    ICON_FILE_ZIP("icon-file-zip"),
    /**
     * The icon to use for mail files
     */
    ICON_MAIL("icon-mail");

    private final String icon;

    FileIcons(String icon) {
        this.icon = icon;
    }

    /**
     * Get the icon to use
     * @return the icon
     */
    public String getIconName() {
        return icon;
    }
}
