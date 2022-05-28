package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.ConfigServiceRegistry;
import com.legyver.fenxlib.api.config.FileAwareApplicationConfig;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;

/**
 * Lifecycle hook to auto-save the application config data on shutdown
 */
public class AutoSaveConfigApplicationLifecycleHook implements ApplicationLifecycleHook {
	private final String appConfigFileName;

	/**
	 * Construct an application lifecycle hook to auto-save the application state to the config.
	 * @param appConfigFileName the application config filename
	 */
	public AutoSaveConfigApplicationLifecycleHook(String appConfigFileName) {
		this.appConfigFileName = appConfigFileName;
	}

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_SHUTDOWN;
	}

	/**
	 * We want the sync hooks to fire before this. If you want something autosaved have a higher priority (smaller number) assigned to your hook.
	 * @return 2500
	 */
	@Override
	public int getPriority() {
		return 2500;
	}

	@Override
	public String getName() {
		return AutoSaveConfigApplicationLifecycleHook.class.getSimpleName();
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			FileAwareApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
			saveFile(applicationConfig);
			return;
		};
	}

	private void saveFile(FileAwareApplicationConfig applicationConfig) throws CoreException {
		ConfigServiceRegistry.getInstance().saveConfig(appConfigFileName, applicationConfig);
	}

}
