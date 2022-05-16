package com.legyver.fenxlib.api.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.adapter.ConfigAdapters;
import com.legyver.fenxlib.api.config.adapter.IConfigAdapter;
import com.legyver.fenxlib.api.config.parts.IRecentlyViewedFile;
import com.legyver.fenxlib.api.service.OrderedServiceDelegator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

/**
 * Loads the config from any ConfigService registered.
 * It is thought that impl will provide one and tests have the option to provide one (when reading from classpath resource)
 */
public class ConfigServiceRegistry {
	private static final Logger logger = LogManager.getLogger(ConfigServiceRegistry.class);

	private final OrderedServiceDelegator<ConfigService> orderedServiceDelegator;
	private static ConfigServiceRegistry instance;
	private Consumer<ConfigService> configServiceInitializer;
	boolean initialized;

	private ConfigServiceRegistry() {
		ServiceLoader<ConfigService> configServiceLoader = ServiceLoader.load(ConfigService.class);
		orderedServiceDelegator = new OrderedServiceDelegator<>(configServiceLoader);
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

	private synchronized void initialize() {
		if (!initialized) {
			for (Iterator<ConfigService> it = orderedServiceDelegator.iterator(); it.hasNext(); ) {
				if (configServiceInitializer != null) {
					configServiceInitializer.accept(it.next());
				}
			}
		}
		initialized = true;
	}

	/**
	 * Load the config from the first {@link ConfigService} that returns a non-null result.
	 * @param <T> the type of the config file
	 * @param filename the filename
	 * @return the config
	 */
	public <T extends IApplicationConfig> T loadConfig(String filename) {
		if (!initialized) {
			initialize();
		}
		T result = null;
		for (Iterator<ConfigService> it = orderedServiceDelegator.iterator(); result == null && it.hasNext(); ) {
			ConfigService next = it.next();
			try {
				result = (T) next.loadConfig(filename);
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
		if (!initialized) {
			initialize();
		}
		for (Iterator<ConfigService> it = orderedServiceDelegator.iterator(); !saved && it.hasNext(); ) {
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

	/**
	 * Adapt a recently-viewed file to a format appropriate for the config
	 * @param file teh file to adapt
	 * @param <T> the class of the recently-viewed reference object
	 * @return the reference object for the recently-viewed file
	 * @throws CoreException if there is an error adapting the file
	 */
	public <T extends IRecentlyViewedFile> T adaptRecentlyViewedFile(File file) throws CoreException {
		IConfigAdapter<File, IRecentlyViewedFile> adapter = orderedServiceDelegator.getDelegate().getAdapter(ConfigAdapters.RECENTLY_VIEWED_FILE.name());
		return (T) adapter.adapt(file);
	}

	/**
	 * Set the initializer to use to initialize the config service
	 * @param configServiceInitializer the initializer
	 */
	public void setConfigServiceInitializer(Consumer<ConfigService> configServiceInitializer) {
		this.configServiceInitializer = configServiceInitializer;
	}
}
