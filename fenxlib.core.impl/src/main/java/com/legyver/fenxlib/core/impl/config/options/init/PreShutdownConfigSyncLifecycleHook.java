package com.legyver.fenxlib.core.impl.config.options.init;

import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.impl.config.ApplicationConfig;
import com.legyver.fenxlib.core.impl.files.FileRegistry;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import com.legyver.fenxlib.core.impl.config.parts.ILastOpened;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import javafx.collections.ObservableList;

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
	 */
	protected void syncToConfig(T applicationConfig) {
		updateLastOpenedDirectory(applicationConfig);
	}

	private void updateLastOpenedDirectory(ApplicationConfig applicationConfig) {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		ObservableList<FileOptions> openFiles = fileRegistry.getOpenFiles();

		if (!openFiles.isEmpty()) {
			ILastOpened lastOpened = applicationConfig.getLastOpened();
			FileOptions mostRecent = openFiles.get(openFiles.size() -1);
			lastOpened.setLastFile(mostRecent.getFilePath());
		}
	}
}
