package com.legyver.fenxlib.core.config.options.mixins;

import com.legyver.fenxlib.core.config.ApplicationConfig;
import com.legyver.fenxlib.core.config.ApplicationConfigHandler;
import com.legyver.fenxlib.core.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;
import com.legyver.util.adaptex.ExceptionToCoreExceptionFunctionDecorator;

public class LoadConfigMixin implements HookRegistrationMixin {
	private final ApplicationConfigHandler applicationConfigHandler;
	private final ApplicationConfigProvider applicationConfigProvider;

	public LoadConfigMixin(ApplicationConfigHandler applicationConfigHandler, ApplicationConfigProvider applicationConfigProvider) {
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
