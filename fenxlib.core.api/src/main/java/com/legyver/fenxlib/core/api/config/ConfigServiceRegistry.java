package com.legyver.fenxlib.core.api.config;

import com.legyver.core.exception.CoreException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Loads the config from any ConfigService registered.
 * It is thought that impl will provide one and tests have the option to provide one (when reading from classpath resource)
 */
public class ConfigServiceRegistry {
	private static final Logger logger = LogManager.getLogger(ConfigServiceRegistry.class);

	private final List<ConfigService> configServices;
	private static ConfigServiceRegistry instance;

	private ConfigServiceRegistry() {
		ServiceLoader<ConfigService> configServiceLoader = ServiceLoader.load(ConfigService.class);

		//make sure the config services are sorted in order of preference
		//for tests this will be test first
		//for production code, this will be whatever is defined although in this case there should only be one
		List<ConfigService> services = new ArrayList<>();
		for (Iterator<ConfigService> it = configServiceLoader.iterator(); it.hasNext(); ) {
			ConfigService next = it.next();
			services.add(next);
		}
		Collections.sort(services);
		configServices = services;
	}

	/**
	 * Get the singleton instance of the ConfigServiceRegistry
	 * @return the registry
	 */
	public static ConfigServiceRegistry getInstance() {
		if (instance == null) {
			synchronized (ConfigServiceRegistry.class) {
				if (instance == null) {
					instance = new ConfigServiceRegistry();
				}
			}
		}
		return instance;
	}

	/**
	 * Load the config from the first {@link ConfigService} that returns a non-null result.
	 * @param filename the filename
	 * @param <T> the type of the config file
	 * @return the config
	 */
	public <T extends IApplicationConfig> T loadConfig(String filename) {
		T result = null;
		for (Iterator<ConfigService> it = configServices.iterator(); result == null && it.hasNext(); ) {
			ConfigService next = it.next();
			try {
				result = next.loadConfig(filename);
			} catch (CoreException e) {
				logger.error("Unable to read file[" + filename + "] with " + next.getClass(), e);
			}
		}
		return result;
	}

	/**
	 * Save the config with the first {@link ConfigService} that returns a true flag.
	 * If no service is able to save the config, an error message is logged.
	 * @param applicationConfigFilename the filename to use
	 * @param applicationConfig the config file to save
	 * @param <T> the type of the config file
	 */
	public <T extends IApplicationConfig> void saveConfig(String applicationConfigFilename, T applicationConfig) {
		boolean saved = false;
		for (Iterator<ConfigService> it = configServices.iterator(); !saved && it.hasNext(); ) {
			ConfigService next = it.next();
			try {
				saved = next.saveConfig(applicationConfigFilename, applicationConfig);
			} catch (CoreException e) {
				logger.error("Unable to save config[" + applicationConfigFilename + "] with " + next.getClass(), e);
			}
		}
		if (!saved) {
			logger.error("Unable to save file");
		}
	}
}
