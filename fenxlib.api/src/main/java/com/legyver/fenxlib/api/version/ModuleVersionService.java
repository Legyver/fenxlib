package com.legyver.fenxlib.api.version;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.service.OrderedService;

/**
 * Modules that write to a config are required to be able to specify their names and versions.
 * This is done so that config file portions can be versioned independently.
 * This service provides the module versions of the fenxlib library components as well as any additional libraries
 * that follow the established convention (ie: implementing a ModuleVersionService to provide the module name and version).
 *
 * To version the config section with the fenxlib library,
 * your ConfigSection must implement FenxlibVersionedConfigSection
 */
public interface ModuleVersionService extends OrderedService<ModuleVersionService> {
    /**
     * Get the version of the module.
     * This is used to determine any migration that needs to be done when reading a module's config.
     * @return the version
     * @throws CoreException if there is an error reading the information from file
     */
    String getVersion() throws CoreException;

    /**
     * Get the name of the module
     * @return the name of the module
     * @throws CoreException if there is an error reading the information from file
     */
    String getModuleName() throws CoreException;

}
