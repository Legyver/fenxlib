package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.config.ApplicationConfig;
import com.legyver.fenxlib.core.config.parts.ILastOpened;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.files.FileOptions;
import com.legyver.fenxlib.core.files.FileRegistry;
import com.legyver.fenxlib.core.lifecycle.LifecyclePhase;
import javafx.collections.ObservableList;

/**
 * Lifecycle hook to sync any information about the application state (recently-viewed files, etc) to the application config file
 * @param <T> the type of ApplicationContext
 */
public class PreShutdownConfigSyncLifecycleHook<T extends ApplicationConfig> implements ApplicationLifecycleHook {

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_SHUTDOWN;
	}

	public int getPriority() {
		return 10;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			T applicationConfig = (T) ApplicationContext.getApplicationConfig();
			syncToConfig(applicationConfig);
		};
	}

	/**
	 * Override this method when you register your hook if you want something other than the recentFiles synced.
	 * It will not replace the default hook, but you can register a separate one to sync other items.
	 * @param applicationConfig the application config
	 * @throws CoreException if there is an error reading the last-opened file from config
	 */
	protected void syncToConfig(T applicationConfig) throws CoreException {
		updateLastOpenedDirectory(applicationConfig);
	}

	private void updateLastOpenedDirectory(ApplicationConfig applicationConfig) throws CoreException {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		ObservableList<FileOptions> openFiles = fileRegistry.getOpenFiles();

		if (!openFiles.isEmpty()) {
			ILastOpened lastOpened = applicationConfig.getLastOpened();
			FileOptions mostRecent = openFiles.get(openFiles.size() -1);
			lastOpened.setLastFile(mostRecent.getFilePath());
		}
	}
}
