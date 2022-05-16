package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.api.config.FileAwareApplicationConfig;
import com.legyver.fenxlib.api.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.api.config.ConfigServiceRegistry;
import com.legyver.fenxlib.api.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;

import java.util.HashMap;

/**
 * Lifecycle hook to load the application config from file on startup
 */
public class LoadConfigApplicationLifecycleHook implements ApplicationLifecycleHook {
	private final ApplicationConfigInstantiator appConfigInstantiator;
	private final ApplicationConfigProvider applicationConfigProvider;

	/**
	 * Construct an Application Lifecycle Hook to load the config.
	 * By default this happens during {@link LifecyclePhase#PRE_INIT}
	 * @param appConfigInstantiator instantiates the config if it does not exist
	 * @param applicationConfigProvider provides the config filename
	 */
	public LoadConfigApplicationLifecycleHook(ApplicationConfigInstantiator appConfigInstantiator, ApplicationConfigProvider applicationConfigProvider) {
		this.appConfigInstantiator = appConfigInstantiator;
		this.applicationConfigProvider = applicationConfigProvider;
	}

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_INIT;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			ConfigServiceRegistry.getInstance().setConfigServiceInitializer(configService -> configService.init(appConfigInstantiator));
			FileAwareApplicationConfig applicationConfig = ConfigServiceRegistry.getInstance().loadConfig(applicationConfigProvider.getApplicationConfigFilename());
			if (applicationConfig == null) {
				applicationConfig = (FileAwareApplicationConfig) appConfigInstantiator.init(new HashMap());
			}
			ApplicationContext.setApplicationConfig(applicationConfig);
		};
	}

	@Override
	public String getName() {
		return LoadConfigApplicationLifecycleHook.class.getSimpleName();
	}
}
