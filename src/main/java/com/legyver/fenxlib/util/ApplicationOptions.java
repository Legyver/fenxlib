package com.legyver.fenxlib.util;

import com.legyver.fenxlib.uimodel.IUiModel;
import javafx.stage.Stage;
import com.legyver.fenxlib.locator.IComponentRegistry;
import com.legyver.fenxlib.util.hook.LifecycleHook;
import java.util.List;

public interface ApplicationOptions<T extends IComponentRegistry, U extends IUiModel> {

	Stage getPrimaryStage();

	T getComponentRegistry();

	U getUiModel();

	void executeHook(LifecycleHook hook);

	List<LoadableResource> getIconFontSVGFiles();
}
