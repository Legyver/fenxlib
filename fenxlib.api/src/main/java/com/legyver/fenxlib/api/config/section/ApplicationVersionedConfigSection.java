package com.legyver.fenxlib.api.config.section;

import com.legyver.fenxlib.api.context.ApplicationContext;

/**
 * Config section that is versioned the same as the application.
 */
public interface ApplicationVersionedConfigSection extends ConfigSection {
    /**
     * Get the version of the config section
     * @return the application version from the {@link ApplicationContext#getApplicationVersion()}
     */
    default String getVersion() {
        return ApplicationContext.getApplicationVersion();
    }
}
