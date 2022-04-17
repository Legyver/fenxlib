package com.legyver.fenxlib.core.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.service.OrderedService;

/**
 * Loads the application config from file.
 * This was moved to a service to make tests more sensible.
 */
public interface ConfigService<I> extends OrderedService<ConfigService>, InitializableService<I> {
	/**
	 * Load the application config from file.
	 * @param filename the filename
	 * @param <T> the type of the config
	 * @return the config
	 * @throws CoreException if the file does not exist or there is an error reading it
	 */
	<T extends IApplicationConfig> T loadConfig(String filename) throws CoreException;

	/**
	 * Write the application config to file
	 * @param filename the filename
	 * @param config the config
	 * @param <T> the type of config
	 * @return true if save successful
	 * @throws CoreException if there is an error during write
	 */
	<T extends IApplicationConfig> boolean saveConfig(String filename, T config) throws CoreException;

}
