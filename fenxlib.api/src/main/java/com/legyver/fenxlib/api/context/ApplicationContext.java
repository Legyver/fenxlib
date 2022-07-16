package com.legyver.fenxlib.api.context;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.alert.AlertServiceRegistry;
import com.legyver.fenxlib.api.alert.Level;
import com.legyver.fenxlib.api.config.IApplicationConfig;
import com.legyver.fenxlib.api.config.load.ApplicationHome;
import com.legyver.fenxlib.api.files.FileRegistry;
import com.legyver.fenxlib.api.icons.application.IconAliasMap;
import com.legyver.fenxlib.api.lifecycle.IApplicationLifecycleHookRegistry;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.locator.query.QueryableComponentRegistry;
import com.legyver.fenxlib.api.logging.LazyLog;
import com.legyver.fenxlib.api.uimodel.IUiModel;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.List;

/**
 * Base ApplicationContext that has the minimum static information required by the framework
 */
public class ApplicationContext {
	private static final LazyLog logger = new LazyLog(ApplicationContext.class);

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
	private static ApplicationHome applicationHome;
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
	private static QueryableComponentRegistry componentRegistry;

	/**
	 * Any application lifecycle hooks are registered here
	 */
	private static IApplicationLifecycleHookRegistry applicationLifecycleHookRegistry;

	private static List<URL> stylesheets;
	private static IconAliasMap iconAliasMap;

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
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				logger.info("Primary stage closing. Executing SHUTDOWN hooks");
				try {
					ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(LifecyclePhase.SHUTDOWN);
				} catch (CoreException e) {
					logger.error("Error executing shutdown lifecycle hook", e);
				}
			}
		});
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
	 * Get the application home
	 * @return the application home
	 */
	public static ApplicationHome getApplicationHome() {
		return applicationHome;
	}

	/**
	 * Set the application home.
	 * @param applicationHome the application home
	 */
	public static void setApplicationHome(ApplicationHome applicationHome) {
		ApplicationContext.applicationHome = applicationHome;
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
	 * Set the registry to use for registering components
	 * @param componentRegistry the component registry
	 */
	public static void setComponentRegistry(QueryableComponentRegistry componentRegistry) {
		ApplicationContext.componentRegistry = componentRegistry;
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

	/**
	 * Set the stylesheets to be used by the application.
	 * These are automatically added to any new scene when launching a new scene
	 * @param stylesheetURLs the urls of the stylesheets
	 */
	public static void setStylesheets(List<URL> stylesheetURLs) {
		ApplicationContext.stylesheets = stylesheetURLs;
	}

	/**
	 * Get the stylesheets to be used by the application
	 * @return the stylesheets
	 */
	public static List<URL> getStylesheets() {
		return stylesheets;
	}

	/**
	 * Set the icons to use for generic application events or components
	 * @param iconAliasMap the map of icons and their aliases
	 */
	public static void setIconAliasMap(IconAliasMap iconAliasMap) {
		ApplicationContext.iconAliasMap = iconAliasMap;
	}

	/**
	 * Get the alias map of application icons
	 * @return the alias map
	 */
	public static IconAliasMap getIconAliasMap() {
		return iconAliasMap;
	}
}
