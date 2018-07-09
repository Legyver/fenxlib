package com.legyver.fenxlib.util;

import com.legyver.fenxlib.locator.IComponentRegistry;
import com.legyver.fenxlib.util.hook.ExecutableHook;
import com.legyver.fenxlib.util.hook.LifecycleHook;
import java.util.EnumMap;
import javafx.stage.Stage;

public abstract class AbstractApplicationOptions<T extends IComponentRegistry, U extends IUiModel> implements ApplicationOptions<T, U> {

	protected final Stage primaryStage;
	protected final T componentRegistry;
	protected final U uiModel;
	protected final EnumMap<LifecycleHook, ExecutableHook> lifecycleHooks = new EnumMap<>(LifecycleHook.class);

	public AbstractApplicationOptions(Stage primaryStage, T componentRegistry, U uiModel) {
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
