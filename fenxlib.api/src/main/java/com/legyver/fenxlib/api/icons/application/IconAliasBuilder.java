package com.legyver.fenxlib.api.icons.application;

/**
 * Builder for application-icon aliases
 */
public interface IconAliasBuilder {
    /**
     * Build the icon alias map
     * @return the map
     */
    IconAliasMap build();

    /**
     * Specify the given application icon to alias
     * @param iconType the application icon to alias
     * @return the given icon builder
     */
    GivenIconAliasBuilder given(ApplicationIconAffiliated iconType);
}
