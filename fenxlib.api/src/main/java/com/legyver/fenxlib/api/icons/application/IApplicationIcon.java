package com.legyver.fenxlib.api.icons.application;

/**
 * Application icon name-exposing interface.
 *
 * The idea is that applications have things that can be overridden (like the icon for an alert)
 * This is done by having an application-unique name for every such icon.  The rendering component
 * will query the icon registry for the icon to use for this application-unique name.
 * The application unique names are set via ApplicationOptions on the ApplicationContext.
 * If you need to change the icon, you can do so via {@link com.legyver.fenxlib.api.config.options.ApplicationOptions.Builder#mergeIconAliasMap(IconAliasMap)}
 */
public interface IApplicationIcon {
    /**
     * Get the application-unique name for the icon
     * @return the name
     */
    String getIconName();
}
