package com.legyver.fenxlib.core.impl.context;

import com.legyver.fenxlib.core.api.uimodel.IUiModel;
import com.legyver.fenxlib.core.api.context.BaseApplicationContext;
import com.legyver.fenxlib.core.impl.config.ApplicationConfig;
import com.legyver.fenxlib.core.impl.files.FileRegistry;
import com.legyver.fenxlib.core.impl.util.hook.ApplicationLifecycleHookRegistry;
import javafx.stage.Stage;

/**
 * Static container for application framework information.
 */
public class ApplicationContext extends BaseApplicationContext {

	/**
	 * Any file that is opened within the application has a reference placed here
	 */
	private static final FileRegistry fileRegistry = new FileRegistry();
	/**
	 * Any application lifecycle hooks are registered here
	 */
	private static final ApplicationLifecycleHookRegistry applicationLifecycleHookRegistry = new ApplicationLifecycleHookRegistry();
	/**
	 * The UI model for the application
	 */
	private static IUiModel uiModel;
	/**
	 *
	 */
	private static Stage primaryStage;
	/**
	 * The application config file
	 */
	private static ApplicationConfig applicationConfig;

	public static ApplicationLifecycleHookRegistry getApplicationLifecycleHookRegistry() {
		return applicationLifecycleHookRegistry;
	}

	public static FileRegistry getFileRegistry() {
		return fileRegistry;
	}

	public static IUiModel getUiModel() {
		return uiModel;
	}

	public static void setUiModel(IUiModel uiModel) {
		ApplicationContext.uiModel = uiModel;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		ApplicationContext.primaryStage = primaryStage;
	}

	public static ApplicationConfig getApplicationConfig() {
		return applicationConfig;
	}

	public static void setApplicationConfig(ApplicationConfig applicationConfig) {
		ApplicationContext.applicationConfig = applicationConfig;
	}

}
