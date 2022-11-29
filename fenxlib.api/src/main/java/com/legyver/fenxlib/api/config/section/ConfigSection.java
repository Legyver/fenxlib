package com.legyver.fenxlib.api.config.section;

/**
 * A configuration section
 */
public interface ConfigSection {
    /**
     * Get the version of the configuration section
     * @return the version
     */
    String getVersion();

    /**
     * Get the name of the configuration section.
     * In the case of JSON, config, this will match the key for the config section.
     * By convention, for fenxlib libraries, this is the name of your module that is being configured.
     * @return the name of the config section.
     */
    String getSectionName();
}
