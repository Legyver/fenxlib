package com.legyver.fenxlib.core.config.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.Fenxlib;
import com.legyver.fenxlib.core.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.core.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.config.load.ApplicationHome;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.files.util.FileIOUtil;
import com.legyver.fenxlib.core.icons.service.IconLoaderService;
import com.legyver.fenxlib.core.lifecycle.ApplicationLifecycleHookRegistry;
import com.legyver.fenxlib.core.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.core.lifecycle.hooks.*;
import com.legyver.fenxlib.core.uimodel.IUiModel;

import javafx.application.Application;

/**
 * Options for the application
 */
public class ApplicationOptions {
	private final IUiModel uiModel;

	private ApplicationOptions(IUiModel uiModel) {
		this.uiModel = uiModel;
	}

	/**
	 * Starts up application by executing the first three phases: bootstrap, pre-init and init
	 * @param application the application that is being bootstrapped.  If null, HostServices will not be available in supported framework functionality.
	 *                    See {@link ApplicationContext#getHostServices()}
	 * @throws CoreException if there is a problem with any of the startup lifecycle phases
	 */
	public void startup(Application application) throws CoreException {
		Fenxlib.registerApplication(application);
		postInit();
	}

	/**
	 * Bootstraps the application.
	 * Called automatically in ApplicationOptions.Builder.build()
	 * @throws CoreException if there is a problem with the bootstrap phase
	 */
	protected void bootstrap() throws CoreException {
		ApplicationContext.setUiModel(uiModel);
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
	 * Builder to specify application options
	 * @param <B> the subtype of the builder
	 */
	public static class Builder<B extends Builder> {
		/**
		 * The UI model of the application
		 */
		protected IUiModel uiModel;
		/**
		 * Provides the application config file name to the framework
		 */
		protected ApplicationConfigProvider applicationConfigProvider;
		/**
		 * Instantiator for the application config
		 */
		protected ApplicationConfigInstantiator appConfigInstantiator;
		/**
		 * Util for reading/writing files
		 */
		protected FileIOUtil fileIOUtil;
		/**
		 * Hook for loading recent files
		 */
		protected RecentFilesApplicationLifecycleHook recentFilesHook;
		/**
		 * The application name.  This is the directory name that all config and log files specific to the application will be saved in
		 */
		protected String appName;
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

		/**
		 * Build the application options
		 * @return the application options
		 * @throws CoreException if there is an error during validation or bootstrapping the application
		 */
		public ApplicationOptions build() throws CoreException {
			validate();
			defaultUnspecified();
			registerLifecycleHooks();
			ApplicationOptions options = new ApplicationOptions(uiModel);
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
		 * - load the config from the {@link ApplicationHome} if no other mechanism set
		 * - use the default {@link FileIOUtil} if no other FileIOUtil set
		 * - load fonts by using the {@link IconLoaderService} if no other glyph-loading hook set
		 * - populate the recent files menu via the default {@link RecentFilesApplicationLifecycleHook} if no other mechanism RecentFilesApplicationLifecycleHook
		 */
		protected void defaultUnspecified() {
			if (applicationConfigProvider == null) {
				applicationConfigProvider = new ApplicationHome(appName);
			}
			if (fileIOUtil == null) {
				fileIOUtil = new FileIOUtil(appConfigInstantiator);
			}
			if (recentFilesHook == null) {
				recentFilesHook = new RecentFilesApplicationLifecycleHook();
			}

		}

		/**
		 * Register lifecycle hooks to
		 * - enable logging (if enabled)
		 * - load the application config
		 * - auto-save config on application exit (if enabled)
		 * - load glyphs on application bootstrap
		 * - read any recent files into the UI Model
		 */
		protected void registerLifecycleHooks() {
			ApplicationContext.setApplicationLifecycleHookRegistry(new ApplicationLifecycleHookRegistry());
			if (enableLogging) {
				if (applicationConfigProvider instanceof ApplicationHome) {
					registerLifecycleHook(new InitLoggingApplicationLifecycleHook((ApplicationHome) applicationConfigProvider, appName));
				} else {
					registerLifecycleHook(new InitLoggingApplicationLifecycleHook(appName));
				}
			}
			//load config Mixin
			registerLifecycleHook(new LoadConfigApplicationLifecycleHook(appConfigInstantiator, applicationConfigProvider));
			//save config Mixin
			if (autosaveConfig) {
				registerLifecycleHook(new PreShutdownConfigSyncLifecycleHook());
				registerLifecycleHook(new AutoSaveConfigApplicationLifecycleHook(applicationConfigProvider));
			}
			//remember recent files
			registerLifecycleHook(recentFilesHook);
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
		 * Specify the provider of the application config
		 * @param applicationConfigProvider the provider for the config file
		 * @return this builder
		 */
		public B customAppConfigProvider(ApplicationConfigProvider applicationConfigProvider) {
			return set(() -> this.applicationConfigProvider = applicationConfigProvider);
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
			return set(()-> ApplicationContext.getApplicationLifecycleHookRegistry()
					.registerHook(applicationLifecycleHook.getLifecyclePhase(), applicationLifecycleHook.getExecutableHook(), applicationLifecycleHook.getPriority()));
		}

		B set(NoArgFunction noArgFunction) {
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
