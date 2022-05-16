package com.legyver.fenxlib.api.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.adapter.IConfigAdapter;
import com.legyver.fenxlib.api.service.OrderedService;

/**
 * Loads the application config from file.
 * This was moved to a service to make tests more sensible.
 */
public interface ConfigService<I, C extends IApplicationConfig> extends OrderedService<ConfigService>, InitializableService<I> {
	/**
	 * Load the application config from file.
	 * @param filename the filename
	 * @return the config
	 * @throws CoreException if the file does not exist or there is an error reading it
	 */
	C loadConfig(String filename) throws CoreException;

	/**
	 * Write the application config to file
	 * @param filename the filename
	 * @param config the config
	 * @return true if save successful
	 * @throws CoreException if there is an error during write
	 */
	boolean saveConfig(String filename, C config) throws CoreException;

	/**
	 * Get the requested adapter
	 * @param forName the adapter to get.  This may be one of {@link com.legyver.fenxlib.api.config.adapter.ConfigAdapters}
	 * @return the adapter
	 */
	IConfigAdapter getAdapter(String forName);

}
