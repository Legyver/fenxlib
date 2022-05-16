package com.legyver.fenxlib.api.config.adapter;

/**
 * Types of adapters for config file.
 * It's expected that any {@link com.legyver.fenxlib.api.config.ConfigService} will be able to at a minimum provide the appropriate adapter on request
 */
public enum ConfigAdapters {
    /**
     * Recently-viewed file reference adapter used to store references to opened files in the application config
     */
    RECENTLY_VIEWED_FILE;
}
