package com.legyver.fenxlib.api.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.service.InitializableService;
import com.legyver.fenxlib.api.service.OrderedService;

/**
 * Loads the application config from file.
 * This was moved to a service to make tests more sensible.
 */
public interface ConfigService<C extends IApplicationConfig> extends OrderedService<ConfigService>, InitializableService<Class<C>> {
	/**
	 * Load the application config from file.
	 * @param appName the name of the application
	 * @param filename the filename
	 * @return the config
	 * @throws CoreException if the file does not exist or there is an error reading it
	 */
	C loadConfig(String appName, String filename) throws CoreException;

	/**
	 * Write the application config to file
	 * @param filename the filename
	 * @param config the config
	 * @return true if save successful
	 * @throws CoreException if there is an error during write
	 */
	boolean saveConfig(String filename, C config) throws CoreException;

}
