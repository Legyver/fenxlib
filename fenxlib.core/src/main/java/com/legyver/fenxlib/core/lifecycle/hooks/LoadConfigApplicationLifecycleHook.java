package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.fenxlib.core.config.ApplicationConfig;
import com.legyver.fenxlib.core.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.core.config.ConfigServiceRegistry;
import com.legyver.fenxlib.core.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.lifecycle.LifecyclePhase;

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
			ApplicationConfig applicationConfig = ConfigServiceRegistry.getInstance().loadConfig(applicationConfigProvider.getApplicationConfigFilename());
			if (applicationConfig == null) {
				applicationConfig = (ApplicationConfig) appConfigInstantiator.init(new HashMap());
			}
			ApplicationContext.setApplicationConfig(applicationConfig);
		};
	}
}
