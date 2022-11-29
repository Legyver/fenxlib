package com.legyver.fenxlib.api.version;

import com.legyver.core.exception.CoreException;

/**
 * Fenxlib libraries all use a version.properties file with only one property.
 *
 * Ergo, grab the only property key
 */
public abstract class AbstractFenxlibModuleVersionService extends AbstractModuleVersionService {

    @Override
    public String getModuleName() throws CoreException {
        return (String) getPropertiesInternal().keySet().stream().findFirst().get();
    }
}
