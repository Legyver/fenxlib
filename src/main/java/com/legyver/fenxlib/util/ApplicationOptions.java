package com.legyver.fenxlib.util;

import javafx.stage.Stage;
import com.legyver.fenxlib.locator.IComponentRegistry;
import com.legyver.fenxlib.util.hook.LifecycleHook;

public interface ApplicationOptions<T extends IComponentRegistry, U extends IUiModel> {
	Stage getPrimaryStage();

	T getComponentRegistry();

	U getUiModel();

	void executeHook(LifecycleHook hook);
}
