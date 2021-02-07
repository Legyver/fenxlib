package com.legyver.fenxlib.core.impl.config.options.init;

import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.impl.config.ApplicationConfig;
import com.legyver.fenxlib.core.impl.config.ApplicationConfigHandler;
import com.legyver.fenxlib.core.impl.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionConsumerDecorator;

import java.io.File;

public class AutoSaveConfigApplicationLifecycleHook implements ApplicationLifecycleHook {
	private final ApplicationConfigProvider appConfigProvider;
	private final ApplicationConfigHandler applicationConfigHandler;

	public AutoSaveConfigApplicationLifecycleHook(ApplicationConfigProvider applicationConfigProvider, ApplicationConfigHandler applicationConfigHandler) {
		this.appConfigProvider = applicationConfigProvider;
		this.applicationConfigHandler = applicationConfigHandler;
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

	private void saveFile(ApplicationConfig applicationConfig) throws com.legyver.core.exception.CoreException {
		new ExceptionToCoreExceptionConsumerDecorator<>((File file) -> {
			applicationConfig.sync();
			applicationConfigHandler.saveConfig(file);
		}).accept(appConfigProvider.getApplicationConfigFile());
	}


}