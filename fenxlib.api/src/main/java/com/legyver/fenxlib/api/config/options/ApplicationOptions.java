package com.legyver.fenxlib.api.config.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.alert.IAlert;
import com.legyver.fenxlib.api.alert.Level;
import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.context.ResourceScope;
import com.legyver.fenxlib.api.icons.application.IconAliasMap;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.LifecycleHookServiceRegistry;
import com.legyver.fenxlib.api.uimodel.IUiModel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Options for the application
 */
public class ApplicationOptions {
	private final String applicationName;
	private final String applicationVersion;
	private final IUiModel uiModel;
	private final boolean usesLogging;
	private final boolean usesAutoSaveConfig;
	private final String applicationConfigName;
	private final Class applicationConfigType;
	private final EnumMap<ResourceScope, List<URL>> stylesheetURLs;
	private final List<ApplicationLifecycleHook> hooksToRegister;
	private final IconAliasMap iconAliasMap;
	private final EnumMap<Level, IAlert.TargetRegion> alertLevelTargetRegions;
	private final List<String> appResourceBundles;

	private ApplicationOptions(String applicationName, String applicationVersion, IUiModel uiModel, boolean usesLogging, boolean usesAutoSaveConfig, String appConfigName, Class applicationConfigType, EnumMap<ResourceScope, List<URL>> stylesheetURLs, List<ApplicationLifecycleHook> hooksToRegister, IconAliasMap iconAliasMap, EnumMap<Level, IAlert.TargetRegion> alertLevelTargetRegions, List<String> appResourceBundles) {
		this.applicationName = applicationName;
		this.applicationVersion = applicationVersion;
		this.uiModel = uiModel;
		this.usesLogging = usesLogging;
		this.usesAutoSaveConfig = usesAutoSaveConfig;
		this.applicationConfigName = appConfigName;
		this.applicationConfigType = applicationConfigType;
		this.stylesheetURLs = stylesheetURLs;
		this.hooksToRegister = hooksToRegister;
		this.iconAliasMap = iconAliasMap;
		this.alertLevelTargetRegions = alertLevelTargetRegions;
		this.appResourceBundles = appResourceBundles;
	}

	/**
	 * Starts up application by executing the first three phases: bootstrap, pre-init and init
	 * @param application the application that is being bootstrapped.  If null, HostServices will not be available in supported framework functionality.
	 *                    See {@link ApplicationContext#getHostServices()}
	 * @param primaryStage the primary stage for the application. If null, popups and new stages will not be able to function correctly
	 * @throws CoreException if there is a problem with any of the startup lifecycle phases
	 */
	public void startup(Application application, Stage primaryStage) throws CoreException {
		Fenxlib.registerApplication(application);
		ApplicationContext.registerStage(primaryStage);
		for (ResourceScope resourceScope : stylesheetURLs.keySet()) {
			ApplicationContext.setStylesheetsForScope(resourceScope, stylesheetURLs.get(resourceScope));
		}
		ApplicationContext.setIconAliasMap(iconAliasMap);
		postInit();
	}

	/**
	 * Bootstraps the application.
	 * Called automatically in ApplicationOptions.Builder.build()
	 * - Sets the UI model on the Application Context
	 * - Initializes all application lifecycle hooks
	 * @throws CoreException if there is a problem with the bootstrap phase
	 */
	protected void bootstrap() throws CoreException {
		ApplicationContext.setUiModel(uiModel);
		ApplicationContext.setApplicationVersion(applicationVersion);
		LifecycleHookServiceRegistry.getInstance().loadLifecycleHooks(this);
		executePhase(LifecyclePhase.BOOTSTRAP);
	}

	/**
	 * Executes the first phase of a multi-phase init application lifecycle
	 * @throws CoreException if there is a problem with the pre_init phase
	 */
	public void preInit() throws CoreException {
		executePhase(LifecyclePhase.PRE_INIT);
	}

	/**
	 * Executes the first two phases of a multi-phase init application lifecycle
	 * @throws CoreException if there is a problem with the pre_init phase
	 */
	public void init() throws CoreException {
		executePhase(LifecyclePhase.INIT);
	}

	/**
	 * Execute up to and including POST_INIT
	 * @throws CoreException if there is an error executing any of the hooks
	 */
	public void postInit() throws CoreException {
		executePhase(LifecyclePhase.POST_INIT);
	}

	/**
	 * Execute up to and including a specific lifecycle phase
	 * @param phase the phase to execute up to (inclusive)
	 * @throws CoreException if there is an error executing any of the hooks
	 */
	protected void executePhase(LifecyclePhase phase) throws CoreException {
		ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(phase);
	}

	/**
	 * Get the UI model
	 * @return the ui model
	 */
	public IUiModel getUiModel() {
		return uiModel;
	}

	/**
	 * Check if the application uses logging
	 * @return true if the application uses logging
	 */
	public boolean isUsesLogging() {
		return usesLogging;
	}

	/**
	 * Check if the application uses an config file that should be autosaved
	 * @return true if the framework should autosave the config
	 */
	public boolean isUsesAutoSaveConfig() {
		return usesAutoSaveConfig;
	}

	/**
	 * Get the instantiator responsible for instantiating the application config
	 *
	 * @return the application config instantiator
	 */
	public Class getApplicationConfigType() {
		return applicationConfigType;
	}

	/**
	 * Get the application name
	 * @return the application name
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * Get the application config file name
	 * @return the config file name
	 */
	public String getApplicationConfigName() {
		return applicationConfigName;
	}

	/**
	 * Get the default regions to display any application alerts
	 * @return the application alert region map
	 */
	public EnumMap<Level, IAlert.TargetRegion> getAlertLevelTargetRegions() {
		return alertLevelTargetRegions;
	}

	/**
	 * Get any additional application hooks to register.  Does not include the hooks that are instantiated based on boolean flags
	 * @return the list of application lifecycle hooks to register
	 */
	public List<ApplicationLifecycleHook> getHooksToRegister() {
		return hooksToRegister;
	}

	/**
	 * Get the resources bundles for the application.
	 * @return the resource bundles.
	 */
	public List<String> getAppResourceBundles() {
		return appResourceBundles;
	}

	/**
	 * Builder to specify application options
	 * @param <B> the subtype of the builder
	 */
	public static class Builder<B extends Builder> {
		/**
		 * The UI model of the application
		 */
		protected IUiModel uiModel;
		/**
		 * The class of the application config
		 */
		protected Class applicationConfigType = ApplicationConfig.class;
		/**
		 * The application name.  This is the directory name that all config and log files specific to the application will be saved in
		 */
		protected String appName;
		/**
		 * The application config name.  If not specified, it will default to the same as the appName with a .json extension
		 */
		protected String appConfigName;
		/**
		 * The application version
		 */
		protected String applicationVersion;

		/**
		 * The application resource bundles to read
		 */
		protected List<String> appResourceBundles = new ArrayList<>();
		/**
		 * Flag to enable autosave of the application config (on by default)
		 */
		protected boolean autosaveConfig = true;
		/**
		 * Flag to enable logging (on by default)
		 */
		protected boolean enableLogging = true;
		/**
		 * Flag to remember opened files (on by default)
		 */
		protected boolean rememberOpenedFiles = true;

		private EnumMap<ResourceScope, List<URL>> stylesheetUrls = new EnumMap<>(ResourceScope.class);

		private EnumMap<Level, IAlert.TargetRegion> alertLevelTargetRegions = new EnumMap<>(Level.class);

		private List<ApplicationLifecycleHook> hooksToRegister = new ArrayList<>();
		private IconAliasMap iconAliasMap = IconAliasMap.withDefaultAlertIcons().build();

		/**
		 * Construct a builder.
		 * By default, application alerts are displayed as popups in the bottom right of the application screen for all levels of alert.
		 */
		public Builder() {
			alertLevelTargetRegions.put(Level.INFO, IAlert.TargetRegion.APPLICATION_BOTTOM_RIGHT);
			alertLevelTargetRegions.put(Level.ERROR, IAlert.TargetRegion.APPLICATION_BOTTOM_RIGHT);
			alertLevelTargetRegions.put(Level.WARNING, IAlert.TargetRegion.APPLICATION_BOTTOM_RIGHT);
		}

		/**
		 * Build the application options
		 * @return the application options
		 * @throws CoreException if there is an error during validation or bootstrapping the application
		 */
		public ApplicationOptions build() throws CoreException {
			validate();
			defaultUnspecified();
			ApplicationOptions options = new ApplicationOptions(
					appName,
					applicationVersion, uiModel,
					enableLogging,
					autosaveConfig,
					appConfigName,
					applicationConfigType,
					stylesheetUrls,
					hooksToRegister,
					iconAliasMap,
					alertLevelTargetRegions,
					appResourceBundles
			);
			options.bootstrap();
			return options;
		}

		/**
		 * Validate the ApplicationOptions
		 * @throws CoreException if required values are not specified
		 */
		protected void validate() throws CoreException {
			if (appName == null) {
				throw new CoreException("Application name is required");
			}
		}

		/**
		 * Default unspecified values
		 * - if the appConfigName has not been set, default it to the appName + .json file extension
		 */
		protected void defaultUnspecified() {
			if (appConfigName == null) {
				appConfigName = appName + ".json";
			}
		}

		/**
		 * Specify the UI model to use
		 * @param uiModel the UI model
		 * @return this builder
		 */
		public B uiModel(IUiModel uiModel) {
			return set(()-> this.uiModel = uiModel);
		}

		/**
		 * Specify the application name.  This is the directory name that all config and log files specific to the application will be saved in
		 * @param appName the app name
		 * @return this builder
		 */
		public B appName(String appName) {
			return set(() -> this.appName = appName);
		}

		/**
		 * Specify the application config name.
		 * @param appConfigName the application config filename name
		 * @return this builder
		 */
		public B appConfigName(String appConfigName) {
			return set(() -> this.appConfigName = appConfigName);
		}

		/**
		 * Soecify the version of the application
		 * @param applicationVersion the version of the application
		 * @return the application version
		 */
		public B appVersion(String applicationVersion) {
			return set(() -> this.applicationVersion = applicationVersion);
		}

		/**
		 * Specify URLs for style sheets
		 * @param url the URL of the stylesheet to include
		 * @param resourceScopes the scopes in which to apply the stylesheets
		 * @return this builder
		 */
		public B styleSheetUrl(URL url, ResourceScope... resourceScopes) {
			return set(() -> {
				if (resourceScopes == null || resourceScopes.length == 0) {
					addScope(ResourceScope.APPLICATION, url);
				} else {
					for (ResourceScope resourceScope : resourceScopes) {
						addScope(resourceScope, url);
					}
				}
			});
		}

		private void addScope(ResourceScope resourceScope, URL url) {
			List<URL> urls = stylesheetUrls.computeIfAbsent(resourceScope, x -> new ArrayList<>());
			urls.add(url);
		}

		/**
		 * Disable auto-saving the config when exiting the application (on by default)
		 * @return this builder
		 */
		public B disableAutosaveConfig() {
			return set(()-> this.autosaveConfig = false);
		}

		/**
		 * Disable logging in the application (on by default)
		 * @return this builder
		 */
		public B disableLogging() {
			return set(() -> this.enableLogging = false);
		}

		/**
		 * Disable remembering opened files (on by default)
		 * @return this builder
		 */
		public B disableRememberOpenedFiles() {
			return set(() -> this.rememberOpenedFiles = false);
		}

		/**
		 * Specify the AppConfig instantiator to use when initializing an application config file
		 * @param applicationConfigType the class of the application config
		 * @return this builder
		 */
		public B applicationConfigClass(Class applicationConfigType) {
			return set(() -> this.applicationConfigType = applicationConfigType);
		}

		/**
		 * Specify custom icons to use for the application
		 * @param iconAliasMap map of icon aliases to merge in
		 * @return this builder
		 */
		public B mergeIconAliasMap(IconAliasMap iconAliasMap) {
			if (iconAliasMap != null) {
				this.iconAliasMap.merge(iconAliasMap);
			}
			return (B) this;
		}

		/**
		 * Add resource bundles
		 * @param resourceBundle resource bundle to add
		 * @return this builder
		 */
		public B resourceBundle(String resourceBundle) {
			if (!appResourceBundles.contains(resourceBundle)) {
				appResourceBundles.add(resourceBundle);
			}
			return (B) this;
		}

		/**
		 * Specify where to display alerts for each level
		 * @param level level of alert
		 * @param targetRegion where to display the alert, ie: application-top-right, bottom-right, status-bar, etc
		 * @return this builder
		 */
		public B displayAlerts(Level level, IAlert.TargetRegion targetRegion) {
			return set(() -> this.alertLevelTargetRegions.put(level, targetRegion));
		}

		/**
		 * Register an application lifecycle hook
		 * @param applicationLifecycleHook the hook to register
		 * @return this builder
		 */
		public B registerLifecycleHook(ApplicationLifecycleHook applicationLifecycleHook) {
			return set(()-> hooksToRegister.add(applicationLifecycleHook));
		}

		/**
		 * Execute a setter and return subclass of this builder
		 * @param noArgFunction the function to execute
		 * @return the lowest implementation of this builder
		 */
		protected B set(NoArgFunction noArgFunction) {
			noArgFunction.execute();
			return (B) this;
		}

	}

	/**
	 * Hook to provide a no arg lambda
	 */
	@FunctionalInterface
	public interface NoArgFunction {
		/**
		 * lambda body
		 */
		void execute();
	}

}
