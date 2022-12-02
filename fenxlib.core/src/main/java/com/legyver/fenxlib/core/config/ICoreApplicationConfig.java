package com.legyver.fenxlib.core.config;

import com.legyver.fenxlib.api.config.IApplicationConfig;

/**
 * Application config that has access to the fenxlib.core configuration
 * Provides access to the fenxlib.core configuration
 */
public interface ICoreApplicationConfig extends IApplicationConfig {

    /**
     * Get the core configuration section
     * @return the core configuration section
     */
    CoreConfigSection getCoreConfig();

    /**
     * Set the core config section
     * @param coreConfig the core config section
     */
    void setCoreConfig(CoreConfigSection coreConfig);

}
