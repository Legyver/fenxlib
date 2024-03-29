package com.legyver.fenxlib.api.context;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.alert.AlertServiceRegistry;
import com.legyver.fenxlib.api.alert.AlertTextContent;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Base ApplicationContext that has the minimum static information required by the framework
 */
public class ApplicationContext {
	private static final LazyLog logger = new LazyLog(ApplicationContext.class);


	/**
	 * The application version
	 */
	private static String applicationVersion;

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
	 * Any stages that should be closed when the primary stage is closed
	 */
	private static List<Stage> subsidiaryStages = new ArrayList<>();
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

	private static EnumMap<ResourceScope, List<URL>> stylesheets = new EnumMap<>(ResourceScope.class);
	private static IconAliasMap iconAliasMap;

	/**
	 *  This can either be specified manually by {@link com.legyver.fenxlib.api.config.options.ApplicationOptions}
	 * 	or, if using fenxlib.widgets.about, can be read from the property file supplying the version to that widget.
	 * @return the application version specified in one of the two aforementioned sources
	 */
	public static String getApplicationVersion() {
		return applicationVersion;
	}

	/**
	 * Set the application version
	 * @param applicationVersion the application version
	 */
	public static void setApplicationVersion(String applicationVersion) {
		ApplicationContext.applicationVersion = applicationVersion;
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
	private static void setPrimaryStage(Stage primaryStage) {
		ApplicationContext.primaryStage = primaryStage;
		if (primaryStage != null) {
			primaryStage.setOnCloseRequest(event -> {
				logger.info("Primary stage closing. Executing SHUTDOWN hooks");
				try {
					applicationLifecycleHookRegistry.executeHook(LifecyclePhase.SHUTDOWN);
				} catch (CoreException e) {
					logger.error("Error executing shutdown lifecycle hook", e);
				}
				for (Stage stage : subsidiaryStages) {
					stage.close();
				}
			});
		}
	}

	/**
	 * Register a stage with the application context.
	 * When the stage is the primary stage, a listener is added to the primary stage close request property to execute the application shutdown
	 * hooks and close any subsidiary stages.
	 * When the stage is not the primary stage, the stage is added to the list of subsidiary stages to be closed on application exit.
	 * @param stage the stage to register
	 */
	public static void registerStage(Stage stage) {
		if (ApplicationContext.primaryStage == null) {
			setPrimaryStage(stage);
		} else if (ApplicationContext.primaryStage != stage) {
			ApplicationContext.subsidiaryStages.add(stage);
		}
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
	 * @param alertTextContent the message for the alert
	 */
	public static void infoAlert(AlertTextContent alertTextContent) {
		infoAlert(alertTextContent, -1L);
	}

	/**
	 * Broadcast an informational alert
	 * @param alertTextContent the message for the alert
	 * @param timeout the timeout for the alert
	 */
	public static void infoAlert(AlertTextContent alertTextContent, Long timeout) {
		broadcastAlert(alertTextContent, Level.INFO, timeout);
	}

	/**
	 * Broadcast a warning alert
	 * @param alertTextContent the message for the alert
	 */
	public static void warningAlert(AlertTextContent alertTextContent) {
		warningAlert(alertTextContent, -1L);
	}

	/**
	 * Broadcast a warning alert
	 * @param alertTextContent the message for the alert
	 * @param timeout the timeout for the alert
	 */
	public static void warningAlert(AlertTextContent alertTextContent, Long timeout) {
		broadcastAlert(alertTextContent, Level.WARNING, timeout);
	}

	/**
	 * Broadcast an error alert
	 * @param alertTextContent the message for the alert
	 */
	public static void errorAlert(AlertTextContent alertTextContent) {
		broadcastAlert(alertTextContent, Level.ERROR, -1L);
	}

	private static void broadcastAlert(AlertTextContent alertTextContent, Level level, Long timeout) {
		Platform.runLater(() -> {
			AlertServiceRegistry.getInstance().displayAlert(alertTextContent, level, timeout);
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
	 * These are automatically added to any new scene when launching a new scene.
	 * @param resourceScope scope in which to apply these stylesheets
	 * @param stylesheetURLs the urls of the stylesheets
	 */
	public static void setStylesheetsForScope(ResourceScope resourceScope, List<URL> stylesheetURLs) {
		ApplicationContext.stylesheets.put(resourceScope, stylesheetURLs);
	}

	/**
	 * Get the stylesheets to be used by the application in a specific scope
	 * @param resourceScope the scope to get the sylesheets for
	 * @return the stylesheets
	 */
	public static List<URL> getStylesheetsForScope(ResourceScope resourceScope) {
		return stylesheets.get(resourceScope);
	}

	/**
	 * Get the stylesheets to be used by the application
	 * @return the stylesheets
	 */
	public static List<URL> getStylesheets() {
		return getStylesheetsForScope(ResourceScope.APPLICATION);
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
