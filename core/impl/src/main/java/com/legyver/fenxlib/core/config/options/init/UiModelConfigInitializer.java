package com.legyver.fenxlib.core.config.options.init;

import com.legyver.fenxlib.core.config.ApplicationConfig;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.uimodel.IUiModel;
import com.legyver.fenxlib.core.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;

public class UiModelConfigInitializer<T extends ApplicationConfig, U extends IUiModel> implements ApplicationLifecycleHook {
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

	/**
	 * Override this method when you register your hook to sync config to your UiModel.
	 */
	protected void syncToUiModel(T applicationConfig, U uiModel) {
		//template
	}
}
