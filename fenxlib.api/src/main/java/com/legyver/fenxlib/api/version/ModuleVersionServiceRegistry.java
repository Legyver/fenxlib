package com.legyver.fenxlib.api.version;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.service.OrderedServiceDelegator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Registry collecting all the versions of various declaring modules.
 * The primary purpose is to provide migration when the modules persisted configuration changes.
 */
public class ModuleVersionServiceRegistry {

    private static final String FENXLIB_VERSION = "fenxlib.version";
    private static final String FENXLIB_PREFIX = "com.legyver.fenxlib";
    private static ModuleVersionServiceRegistry instance;
    private final ServiceLoader<ModuleVersionService> configServiceLoader;
    private final OrderedServiceDelegator<ModuleVersionService> orderedServiceDelegator;

    private static final Map<String, String> versions = new HashMap<>();

    private ModuleVersionServiceRegistry() {
        configServiceLoader = ServiceLoader.load(ModuleVersionService.class);
        orderedServiceDelegator = new OrderedServiceDelegator<>(configServiceLoader);
    }

    /**
     * Get the singleton instance of the ConfigServiceRegistry
     * @return the registry
     */
    public static ModuleVersionServiceRegistry getInstance() {
        if (instance == null) {
            synchronized (ModuleVersionServiceRegistry.class) {
                if (instance == null) {
                    instance = new ModuleVersionServiceRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Load the version information for the modules
     * @throws CoreException if there is a problem reading version.properties
     */
    public void loadVersions() throws CoreException {
        for (Iterator<ModuleVersionService> iterator = orderedServiceDelegator.iterator(); iterator.hasNext();) {
            ModuleVersionService moduleVersionService = iterator.next();
            String moduleName = moduleVersionService.getModuleName();
            String moduleVersion = moduleVersionService.getVersion();
            versions.put(moduleName, moduleVersion);
            //all fenxlib modules are versioned the same so setup a shortcut so that not every module needs to supply there own service
            if (moduleName.startsWith(FENXLIB_PREFIX)) {
                versions.put(FENXLIB_VERSION, moduleVersion);
            }
        }
    }

    /**
     * Get the version of a specific module
     * @param module the module to lookup the version for
     * @return the version of the supplied module
     */
    public String getVersion(String module) {
        String result = versions.get(module);
        if (result == null && module.startsWith(FENXLIB_PREFIX)) {
            result = getFenxlibVersion();
        }
        return result;
    }

    /**
     * Get the version of the fenxlib module
     * @return the fenxlib version
     */
    public String getFenxlibVersion() {
        return versions.get(FENXLIB_VERSION);
    }
}
