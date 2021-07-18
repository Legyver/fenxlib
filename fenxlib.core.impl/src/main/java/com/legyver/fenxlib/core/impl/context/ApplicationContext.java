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

	/**
	 * Get the registry of application lifecycle hooks
	 * @return the application hook registry
	 */
	public static ApplicationLifecycleHookRegistry getApplicationLifecycleHookRegistry() {
		return applicationLifecycleHookRegistry;
	}

	/**
	 * Get the registry of files opened by the application
	 * @return the file registry
	 */
	public static FileRegistry getFileRegistry() {
		return fileRegistry;
	}

	/**
	 * Get the UI model for the application
	 * @return the UI model
	 */
	public static IUiModel getUiModel() {
		return uiModel;
	}

	/**
	 * Set the UI model on the application context
	 * @param uiModel the UI model
	 */
	public static void setUiModel(IUiModel uiModel) {
		ApplicationContext.uiModel = uiModel;
	}

	/**
	 * Get the primary stage of the application
	 * @return the primary stage of the application
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Set the primary stage on the application context
	 * @param primaryStage the primary stage
	 */
	public static void setPrimaryStage(Stage primaryStage) {
		ApplicationContext.primaryStage = primaryStage;
	}

	/**
	 * Get the application config
	 * @return the application config
	 */
	public static ApplicationConfig getApplicationConfig() {
		return applicationConfig;
	}

	/**
	 * Set the application config on the application context
	 * @param applicationConfig the application config
	 */
	public static void setApplicationConfig(ApplicationConfig applicationConfig) {
		ApplicationContext.applicationConfig = applicationConfig;
	}

}
