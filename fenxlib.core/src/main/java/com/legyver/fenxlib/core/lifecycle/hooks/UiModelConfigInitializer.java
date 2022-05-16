package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.api.config.FileAwareApplicationConfig;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.uimodel.IUiModel;

/**
 * Lifecycle hook to sync aspects of application state from the application config to the UI model when the application starts
 * @param <T> the type of the application config
 * @param <U> the type of the UI model
 */
public class UiModelConfigInitializer<T extends FileAwareApplicationConfig, U extends IUiModel> implements ApplicationLifecycleHook {
	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.INIT;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			T applicationConfig = (T) ApplicationContext.getApplicationConfig();
			U applicationUIModel = (U) ApplicationContext.getUiModel();
			syncToUiModel(applicationConfig, applicationUIModel);
		};
	}

	@Override
	public String getName() {
		return UiModelConfigInitializer.class.getSimpleName();
	}

	/**
	 * Override this method when you register your hook to sync config to your UiModel and/or vise-versa
	 * @param applicationConfig the application config
	 * @param uiModel the ui model
	 * @throws CoreException if an error is raised by your sync code
	 */
	protected void syncToUiModel(T applicationConfig, U uiModel) throws CoreException {
		//template
	}
}
