package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.api.config.parts.LastOpened;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.files.FileRegistry;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.core.config.CoreConfigSection;
import com.legyver.fenxlib.core.config.ICoreApplicationConfig;
import javafx.collections.ObservableList;

/**
 * Lifecycle hook to sync any information about the application state (recently-viewed files, etc) to the application config file
 */
public class PreShutdownConfigSyncLifecycleHook implements ApplicationLifecycleHook {

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_SHUTDOWN;
	}

	public int getPriority() {
		return 10;
	}

	@Override
	public String getName() {
		return PreShutdownConfigSyncLifecycleHook.class.getSimpleName();
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			ApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
			syncToConfig(applicationConfig);
		};
	}

	/**
	 * Override this method when you register your hook if you want something other than the recentFiles synced.
	 * It will not replace the default hook, but you can register a separate one to sync other items.
	 * @param applicationConfig the application config
	 * @throws CoreException if there is an error reading the last-opened file from config
	 */
	protected void syncToConfig(ApplicationConfig applicationConfig) throws CoreException {
		updateLastOpenedDirectory(applicationConfig);
	}

	private void updateLastOpenedDirectory(ApplicationConfig applicationConfig) throws CoreException {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		ObservableList<FileOptions> openFiles = fileRegistry.getOpenFiles();

		if (!openFiles.isEmpty()) {
			if (applicationConfig instanceof ICoreApplicationConfig) {
				CoreConfigSection configSection = ((ICoreApplicationConfig) applicationConfig).getCoreConfig();
				LastOpened lastOpened = configSection.getLastOpened();
				FileOptions mostRecent = openFiles.get(openFiles.size() -1);
				lastOpened.setFilePath(mostRecent.getFilePath());
			}
		}
	}
}
