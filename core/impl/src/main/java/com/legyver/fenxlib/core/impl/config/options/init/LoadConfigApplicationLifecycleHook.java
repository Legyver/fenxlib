package com.legyver.fenxlib.core.impl.config.options.init;

import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.impl.config.ApplicationConfig;
import com.legyver.fenxlib.core.impl.config.ApplicationConfigHandler;
import com.legyver.fenxlib.core.impl.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionFunctionDecorator;

public class LoadConfigApplicationLifecycleHook implements ApplicationLifecycleHook {
	private final ApplicationConfigHandler applicationConfigHandler;
	private final ApplicationConfigProvider applicationConfigProvider;

	public LoadConfigApplicationLifecycleHook(ApplicationConfigHandler applicationConfigHandler, ApplicationConfigProvider applicationConfigProvider) {
		this.applicationConfigHandler = applicationConfigHandler;
		this.applicationConfigProvider = applicationConfigProvider;
	}

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_INIT;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			ApplicationConfig applicationConfig = new ExceptionToCoreExceptionFunctionDecorator<>(dontcare ->
					applicationConfigHandler.loadOrInitConfig(applicationConfigProvider.getApplicationConfigAsString()))
					.apply(null);
			ApplicationContext.setApplicationConfig(applicationConfig);
		};
	}
}
