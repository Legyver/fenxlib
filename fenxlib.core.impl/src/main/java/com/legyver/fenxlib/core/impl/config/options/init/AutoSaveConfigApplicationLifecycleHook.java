package com.legyver.fenxlib.core.impl.config.options.init;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.ConfigServiceRegistry;
import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.config.ApplicationConfig;
import com.legyver.fenxlib.core.impl.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;

/**
 * Lifecycle hook to auto-save the application config data on shutdown
 */
public class AutoSaveConfigApplicationLifecycleHook implements ApplicationLifecycleHook {
	private final ApplicationConfigProvider appConfigProvider;

	/**
	 * Construct an application lifecycle hook to auto-save the application state to the config.
	 * @param applicationConfigProvider the provider for the application config
	 */
	public AutoSaveConfigApplicationLifecycleHook(ApplicationConfigProvider applicationConfigProvider) {
		this.appConfigProvider = applicationConfigProvider;
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
	public ExecutableHook getExecutableHook() {
		return () -> {
			ApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
			saveFile(applicationConfig);
			return;
		};
	}

	private void saveFile(ApplicationConfig applicationConfig) throws CoreException {
		applicationConfig.sync();
		ConfigServiceRegistry.getInstance().saveConfig(appConfigProvider.getApplicationConfigFilename(), applicationConfig);
	}

}
