package com.legyver.fenxlib.api.config.section;

import com.legyver.fenxlib.api.version.ModuleVersionServiceRegistry;

/**
 * Config section that is versioned separately from the fenxlib library.
 * These config sections must supply a unique module name to lookup in the {@link ModuleVersionServiceRegistry}
 * The modules will also need to be tied into the {@link com.legyver.fenxlib.api.version.ModuleVersionService} framework.
 */
public interface IndependentlyVersionedConfigSection extends ConfigSection {
    @Override
    default String getVersion() {
        return ModuleVersionServiceRegistry.getInstance().getVersion(getModuleName());
    }

    /**
     * Get the module name to lookup in the {@link ModuleVersionServiceRegistry}
     * @return the module name
     */
    String getModuleName();
}
