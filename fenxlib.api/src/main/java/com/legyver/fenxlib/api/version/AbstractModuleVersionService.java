package com.legyver.fenxlib.api.version;

import com.legyver.core.exception.CoreException;

import java.io.IOException;
import java.util.Properties;

/**
 * Abstract super class for ModuleVersionServices provided as a convenience.
 */
public abstract class AbstractModuleVersionService implements ModuleVersionService {
    private Properties properties;

    @Override
    public int order() {
        return 0;
    }

    @Override
    public String getVersion() throws CoreException {
        return getPropertiesInternal().getProperty(getModuleName());
    }

    /**
     * Get the properties holding the version data
     * @return the properties
     * @throws CoreException if there is an error reading the properties file
     */
    protected Properties getPropertiesInternal() throws CoreException {
        if (properties == null) {
            properties = new Properties();
            try {
                loadProperties(properties);
            } catch (IOException e) {
                throw new CoreException(e);
            }
        }
        return properties;
    }

    /**
     * Load the properties from the module-managed resource
     * @param properties the properties
     * @throws IOException if there is an error reading the supplying file
     */
    protected abstract void loadProperties(Properties properties) throws IOException;
}
