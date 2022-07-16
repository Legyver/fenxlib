package com.legyver.fenxlib.api.alert;

import com.legyver.fenxlib.api.icons.application.IApplicationIcon;

/**
 * Application alert icons
 */
public enum ApplicationAlertIcons implements IApplicationIcon {
    /**
     * Generic notification icon
     */
    ICON_NOTIFICATION("icon-notification"),
    /**
     * Info icon
     */
    ICON_INFO("icon-info"),
    /**
     * Warning icon
     */
    ICON_WARNING("icon-warning");

    private final String iconName;

    ApplicationAlertIcons(String iconName) {
        this.iconName = iconName;
    }

    @Override
    public String getIconName() {
        return iconName;
    }
}
