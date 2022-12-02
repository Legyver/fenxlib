package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.ConfigServiceRegistry;
import com.legyver.fenxlib.api.config.IApplicationConfig;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Lifecycle hook to load the application config from file on startup
 */
public class LoadConfigApplicationLifecycleHook implements ApplicationLifecycleHook {
	private static final Logger logger = LogManager.getLogger(LoadConfigApplicationLifecycleHook.class);

	private final Class applicationConfigClass;
	private final String appName;
	private final String applicationConfigFileName;

	/**
	 * Construct an Application Lifecycle Hook to load the config.
	 * By default this happens during {@link LifecyclePhase#PRE_INIT}
	 * @param applicationConfigClass instantiates the config if it does not exist
	 * @param appName the name of the application used to determine the application home
	 * @param applicationConfigFileName the config filename
	 */
	public LoadConfigApplicationLifecycleHook(Class applicationConfigClass, String appName, String applicationConfigFileName) {
		this.applicationConfigClass = applicationConfigClass;
		this.appName = appName;
		this.applicationConfigFileName = applicationConfigFileName;
	}

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_INIT;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			ConfigServiceRegistry.getInstance().setConfigServiceInitializer(configService -> configService.init(applicationConfigClass));
			IApplicationConfig applicationConfig = ConfigServiceRegistry.getInstance().loadConfig(appName, applicationConfigFileName);
			if (applicationConfig == null) {
				try {
					applicationConfig = (IApplicationConfig) applicationConfigClass.getDeclaredConstructor().newInstance();
				} catch (Exception e) {
					logger.error(e);
					throw new CoreException(e);
				}
			}
			ApplicationContext.setApplicationConfig(applicationConfig);
		};
	}

	@Override
	public String getName() {
		return LoadConfigApplicationLifecycleHook.class.getSimpleName();
	}
}
