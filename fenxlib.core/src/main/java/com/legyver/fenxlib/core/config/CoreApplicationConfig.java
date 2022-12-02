package com.legyver.fenxlib.core.config;

import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.api.config.section.ConfigPersisted;

/**
 * Application config holding the config section provided as a convenience.
 * You can either extend this or provide this configuration in your ApplicationConfig yourself
 */
public class CoreApplicationConfig extends ApplicationConfig implements ICoreApplicationConfig {

    @ConfigPersisted
    private CoreConfigSection coreConfig = new CoreConfigSection();

    @Override
    public CoreConfigSection getCoreConfig() {
        return coreConfig;
    }

    @Override
    public void setCoreConfig(CoreConfigSection coreConfig) {
        this.coreConfig = coreConfig;
    }
}
