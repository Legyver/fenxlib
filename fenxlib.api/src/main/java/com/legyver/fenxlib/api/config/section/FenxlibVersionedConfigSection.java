package com.legyver.fenxlib.api.config.section;

import com.legyver.fenxlib.api.version.ModuleVersionServiceRegistry;

/**
 * Config section that gets its version from the fenxlib version
 */
public interface FenxlibVersionedConfigSection extends ConfigSection {

    @Override
    default String getVersion() {
        return ModuleVersionServiceRegistry.getInstance().getFenxlibVersion();
    }

}
