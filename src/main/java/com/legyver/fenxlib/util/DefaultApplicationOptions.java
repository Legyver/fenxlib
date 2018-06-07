package com.legyver.fenxlib.util;

import java.util.EnumMap;
import javafx.stage.Stage;
import com.legyver.fenxlib.locator.IComponentRegistry;
import com.legyver.fenxlib.util.hook.ExecutableHook;
import com.legyver.fenxlib.util.hook.LifecycleHook;

public class DefaultApplicationOptions<T extends IComponentRegistry, U extends IUiModel> implements ApplicationOptions<T, U> {

	private final Stage primaryStage;
	private final T componentRegistry;
	private final U uiModel;
	private final EnumMap<LifecycleHook, ExecutableHook> lifecycleHooks = new EnumMap<>(LifecycleHook.class);

	public DefaultApplicationOptions(Stage primaryStage, T componentRegistry, U uiModel) {
		this.primaryStage = primaryStage;
		this.componentRegistry = componentRegistry;
		this.uiModel = uiModel;
	}

	@Override
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public T getComponentRegistry() {
		return componentRegistry;
	}

	@Override
	public U getUiModel() {
		return uiModel;
	}

	@Override
	public void executeHook(LifecycleHook hook) {
		ExecutableHook executableHook = lifecycleHooks.get(hook);
		if (executableHook != null) {
			executableHook.execute();
		}
	}

	public void registerHook(LifecycleHook hook, ExecutableHook executableHook) {
		lifecycleHooks.put(hook, executableHook);
	}


}
