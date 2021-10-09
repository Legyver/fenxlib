package com.legyver.fenxlib.core.impl.context;

import com.legyver.fenxlib.core.api.alert.Level;
import com.legyver.fenxlib.core.api.uimodel.IUiModel;
import com.legyver.fenxlib.core.api.context.BaseApplicationContext;
import com.legyver.fenxlib.core.impl.alert.AlertServiceRegistry;
import com.legyver.fenxlib.core.impl.config.ApplicationConfig;
import com.legyver.fenxlib.core.impl.files.FileRegistry;
import com.legyver.fenxlib.core.impl.util.hook.ApplicationLifecycleHookRegistry;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
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

	/**
	 * Broadcast an informational alert
	 * @param stackPane the StackPane to display the alert over
	 * @param title the title for the alert
	 * @param message the message for the alert
	 */
	public static void infoAlert(StackPane stackPane, String title, String message) {
		infoAlert(stackPane, title, message, -1L);
	}

	/**
	 * Broadcast an informational alert
	 * @param stackPane the StackPane to display the alert over
	 * @param title the title for the alert
	 * @param message the message for the alert
	 * @param timeout the timeout for the alert
	 */
	public static void infoAlert(StackPane stackPane, String title, String message, Long timeout) {
		broadcastAlert(stackPane, title, message, Level.INFO, timeout);
	}

	/**
	 * Broadcast a warning alert
	 * @param stackPane the StackPane to display the alert over
	 * @param title the title for the alert
	 * @param message the message for the alert
	 */
	public static void warningAlert(StackPane stackPane, String title, String message) {
		warningAlert(stackPane, title, message, -1L);
	}

	/**
	 * Broadcast a warning alert
	 * @param stackPane the StackPane to display the alert over
	 * @param title the title for the alert
	 * @param message the message for the alert
	 * @param timeout the timeout for the alert
	 */
	public static void warningAlert(StackPane stackPane, String title, String message, Long timeout) {
		broadcastAlert(stackPane, title, message, Level.WARNING, timeout);
	}

	/**
	 * Broadcast an error alert
	 * @param stackPane the StackPane to display the alert over
	 * @param title the title for the alert
	 * @param message the message for the alert
	 */
	public static void errorAlert(StackPane stackPane, String title, String message) {
		broadcastAlert(stackPane, title, message, Level.ERROR, -1L);
	}

	private static void broadcastAlert(StackPane stackPane, String title, String message, Level level, Long timeout) {
		Platform.runLater(() -> {
			AlertServiceRegistry.getInstance().displayAlert(stackPane, title, message, level, timeout);
		});
	}

}
