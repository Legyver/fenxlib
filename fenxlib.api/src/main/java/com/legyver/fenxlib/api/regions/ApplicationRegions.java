package com.legyver.fenxlib.api.regions;

/**
 * Regions in the application where things like menus might be registered.
 *
 * Used for allowing dynamic lookup of menus/menu-items to enable, or disable items dynamically
 */
public enum ApplicationRegions {
    /**
     * The menus region of the application
     */
    MENUS("menus"),
    /**
     * The center region of the menu bar
     */
    TITLE_BAR_CENTER("menubar_center");
    private final String name;

    ApplicationRegions(String name) {
        this.name = name;
    }

    /**
     * Get the name of the application region
     * @return the application region name
     */
    public String getName() {
        return name;
    }
}
