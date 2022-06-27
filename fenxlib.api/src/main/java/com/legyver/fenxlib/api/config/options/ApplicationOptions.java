package com.legyver.fenxlib.api.config.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.LifecycleHookServiceRegistry;
import com.legyver.fenxlib.api.lifecycle.hooks.RecentFilesApplicationLifecycleHook;
import com.legyver.fenxlib.api.uimodel.IUiModel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Options for the application
 */
public class ApplicationOptions {
	private final String applicationName;
	private final IUiModel uiModel;
	private final boolean usesLogging;
	private final boolean usesAutoSaveConfig;
	private final String applicationConfigName;
	private final ApplicationConfigInstantiator appConfigInstantiator;
	private final List<URL> stylesheetURLs;
	private final List<ApplicationLifecycleHook> hooksToRegister;


	private ApplicationOptions(String applicationName, IUiModel uiModel, boolean usesLogging, boolean usesAutoSaveConfig, String appConfigName, ApplicationConfigInstantiator appConfigInstantiator, List<URL> stylesheetURLs, List<ApplicationLifecycleHook> hooksToRegister) {
		this.applicationName = applicationName;
		this.uiModel = uiModel;
		this.usesLogging = usesLogging;
		this.usesAutoSaveConfig = usesAutoSaveConfig;
		this.applicationConfigName = appConfigName;
		this.appConfigInstantiator = appConfigInstantiator;
		this.stylesheetURLs = stylesheetURLs;
		this.hooksToRegister = hooksToRegister;
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
		ApplicationContext.setPrimaryStage(primaryStage);
		ApplicationContext.setStylesheets(stylesheetURLs);
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
	 * @return the application config instantiator
	 */
	public ApplicationConfigInstantiator getAppConfigInstantiator() {
		return appConfigInstantiator;
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
	 * Get any additional application hooks to register.  Does not include the hooks that are instantiated based on boolean flags
	 * @return the list of application lifecycle hooks to register
	 */
	public List<ApplicationLifecycleHook> getHooksToRegister() {
		return hooksToRegister;
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
		 * Instantiator for the application config
		 */
		protected ApplicationConfigInstantiator appConfigInstantiator;
		/**
		 * Hook for loading recent files
		 */
		protected RecentFilesApplicationLifecycleHook recentFilesHook;
		/**
		 * The application name.  This is the directory name that all config and log files specific to the application will be saved in
		 */
		protected String appName;
		/**
		 * The application config name.  If not specified, it will default to the same as the appName with a .json extension
		 */
		protected String appConfigName;
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

		private List<URL> stylesheetUrls = new ArrayList<>();

		private List<ApplicationLifecycleHook> hooksToRegister = new ArrayList<>();

		/**
		 * Build the application options
		 * @return the application options
		 * @throws CoreException if there is an error during validation or bootstrapping the application
		 */
		public ApplicationOptions build() throws CoreException {
			validate();
			defaultUnspecified();
			ApplicationOptions options = new ApplicationOptions(appName, uiModel, enableLogging, autosaveConfig, appConfigName, appConfigInstantiator, stylesheetUrls, hooksToRegister);
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
			if (appConfigInstantiator == null) {
				throw new CoreException("An ApplicationConfigInstantiator is required");
			}
		}

		/**
		 * Default unspecified values
		 * - if the appConfigName has not been set, default it to the appName + .json file extension
		 * - populate the recent files menu via the default {@link RecentFilesApplicationLifecycleHook} if no other mechanism RecentFilesApplicationLifecycleHook
		 */
		protected void defaultUnspecified() {
			if (appConfigName == null) {
				appConfigName = appName + ".json";
			}
			if (recentFilesHook == null) {
				recentFilesHook = new RecentFilesApplicationLifecycleHook();
			}
			hooksToRegister.add(recentFilesHook);
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
		 * Specify URLs for style sheets
		 * @param url the URL of the stylesheet to include
		 * @return this builder
		 */
		public B styleSheetUrl(URL url) {
			return set(() -> this.stylesheetUrls.add(url));
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
		 * @param appConfigInstantiator the instantiator to use
		 * @return this builder
		 */
		public B customAppConfigInstantiator(ApplicationConfigInstantiator appConfigInstantiator) {
			return set(() -> this.appConfigInstantiator = appConfigInstantiator);
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
