package com.legyver.fenxlib.core.context;

import com.legyver.fenxlib.core.alert.AlertServiceRegistry;
import com.legyver.fenxlib.core.alert.Level;
import com.legyver.fenxlib.core.config.IApplicationConfig;
import com.legyver.fenxlib.core.files.FileRegistry;
import com.legyver.fenxlib.core.lifecycle.IApplicationLifecycleHookRegistry;
import com.legyver.fenxlib.core.locator.query.DefaultComponentRegistry;
import com.legyver.fenxlib.core.locator.query.QueryableComponentRegistry;
import com.legyver.fenxlib.core.uimodel.IUiModel;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Base ApplicationContext that has the minimum static information required by the framework
 */
public class ApplicationContext {
	/**
	 * Any file that is opened within the application has a reference placed here
	 */
	private static final FileRegistry fileRegistry = new FileRegistry();
	/**
	 * Host Services for the client desktop.
	 * This will only not be null if you manually set it from your application.
	 */
	private static HostServices hostServices;

	/**
	 * The UI model for the application
	 */
	private static IUiModel uiModel;
	/**
	 * The primary stage
	 */
	private static Stage primaryStage;
	/**
	 * The application config file
	 */
	private static IApplicationConfig applicationConfig;

	/**
	 * Allow binding factories to bind with supported BindableAppState properties
	 */
	private static final BindableAppState appState = new BindableAppState();

	/**
	 * All factories register their components here on construction.
	 */
	private static final QueryableComponentRegistry componentRegistry = new DefaultComponentRegistry();

	/**
	 * Any application lifecycle hooks are registered here
	 */
	private static IApplicationLifecycleHookRegistry applicationLifecycleHookRegistry;

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
	 * @param <T> the expected class type of the IApplicationConfig
	 * @return the application config
	 */
	public static <T extends IApplicationConfig> T getApplicationConfig() {
		return (T) applicationConfig;
	}

	/**
	 * Set the application config on the application context
	 * @param applicationConfig the application config
	 */
	public static void setApplicationConfig(IApplicationConfig applicationConfig) {
		ApplicationContext.applicationConfig = applicationConfig;
	}

	/**
	 * Broadcast an informational alert
	 * @param title the title for the alert
	 * @param message the message for the alert
	 */
	public static void infoAlert(String title, String message) {
		infoAlert(title, message, -1L);
	}

	/**
	 * Broadcast an informational alert
	 * @param title the title for the alert
	 * @param message the message for the alert
	 * @param timeout the timeout for the alert
	 */
	public static void infoAlert(String title, String message, Long timeout) {
		broadcastAlert(title, message, Level.INFO, timeout);
	}

	/**
	 * Broadcast a warning alert
	 * @param title the title for the alert
	 * @param message the message for the alert
	 */
	public static void warningAlert(String title, String message) {
		warningAlert(title, message, -1L);
	}

	/**
	 * Broadcast a warning alert
	 * @param title the title for the alert
	 * @param message the message for the alert
	 * @param timeout the timeout for the alert
	 */
	public static void warningAlert(String title, String message, Long timeout) {
		broadcastAlert(title, message, Level.WARNING, timeout);
	}

	/**
	 * Broadcast an error alert
	 * @param title the title for the alert
	 * @param message the message for the alert
	 */
	public static void errorAlert(String title, String message) {
		broadcastAlert(title, message, Level.ERROR, -1L);
	}

	private static void broadcastAlert(String title, String message, Level level, Long timeout) {
		Platform.runLater(() -> {
			AlertServiceRegistry.getInstance().displayAlert(title, message, level, timeout);
		});
	}

	/**
	 * Get the state of the application
	 * @return the application state
	 */
	public static BindableAppState getAppState() {
		return appState;
	}

	/**
	 * Get the registry of all registered components of the application
	 * @return the component registry
	 */
	public static QueryableComponentRegistry getComponentRegistry() {
		return componentRegistry;
	}

	/**
	 * Get the registry of application lifecycle hooks
	 * @param <T> The type of IApplicationLifecycleHookRegistry
	 * @return the application hook registry
	 */
	public static <T extends IApplicationLifecycleHookRegistry> T getApplicationLifecycleHookRegistry() {
		return (T) applicationLifecycleHookRegistry;
	}

	/**
	 * Set the registry of application lifecycle hooks
	 * @param applicationLifecycleHookRegistry the application hook registry
	 */
	public static void setApplicationLifecycleHookRegistry(IApplicationLifecycleHookRegistry applicationLifecycleHookRegistry) {
		ApplicationContext.applicationLifecycleHookRegistry = applicationLifecycleHookRegistry;
	}

	/**
	 * Get the HostServices for the client desktop application
	 * This will only not be null if you manually set it from your application or use the ApplicationOptions#startup(Application).
	 * @return the host service if set by application
	 */
	public static HostServices getHostServices() {
		return hostServices;
	}

	/**
	 * Get the HostServices for the client desktop application
	 * @param hostServices host services to use
	 */
	public static void setHostServices(HostServices hostServices) {
		ApplicationContext.hostServices = hostServices;
	}
}
