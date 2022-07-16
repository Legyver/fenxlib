package com.legyver.fenxlib.api.icons.application;

/**
 * Builder for specifying the icon to use
 */
public interface ThenIconAliasBuilder extends IconAliasBuilder {
    /**
     * Specify the icon to use
     * @param icon the icon
     * @return the icon alias builder for finalizing the alias map or specifying additional aliases
     */
    IconAliasBuilder thenUseIcon(String icon);
}
