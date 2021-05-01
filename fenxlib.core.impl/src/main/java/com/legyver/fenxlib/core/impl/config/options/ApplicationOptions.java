package com.legyver.fenxlib.core.impl.config.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.uimodel.IUiModel;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.impl.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.core.impl.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.impl.config.load.ApplicationHome;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.files.FileIOUtil;
import com.legyver.fenxlib.core.impl.config.options.init.*;

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
	 * @throws CoreException if there is a problem with any of the startup lifecycle phases
	 */
	public void startup() throws CoreException {
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
	 * @throws CoreException
	 */
	public void postInit() throws CoreException {
		executePhase(LifecyclePhase.POST_INIT);
	}

	protected void executePhase(LifecyclePhase phase) throws CoreException {
		ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(phase);
	}

	/**
	 * Builder to specify application options
	 * @param <B> the subtype of the builder
	 */
	public static class Builder<B extends Builder> {
		protected IUiModel uiModel;
		protected ApplicationConfigProvider applicationConfigProvider;
		protected ApplicationConfigInstantiator appConfigInstantiator;
		protected FileIOUtil fileIOUtil;
		protected SVGGlyphLoadingApplicationLifecycleHook glyphLoadingHook;
		protected RecentFilesApplicationLifecycleHook recentFilesHook;
		protected String appName;
		protected boolean autosaveConfig = true;
		protected boolean enableLogging = true;
		protected boolean rememberOpenedFiles = true;

		public ApplicationOptions build() throws CoreException {
			validate();
			defaultUnspecified();
			registerLifecycleHooks();
			ApplicationOptions options = new ApplicationOptions(uiModel);
			options.bootstrap();
			return options;
		}

		protected void validate() throws CoreException {
			if (appName == null) {
				throw new CoreException("Application name is required");
			}
			if (appConfigInstantiator == null) {
				throw new CoreException("An ApplicationConfigInstantiator is required");
			}
		}

		protected void defaultUnspecified() {
			if (applicationConfigProvider == null) {
				applicationConfigProvider = new ApplicationHome(appName);
			}
			if (fileIOUtil == null) {
				fileIOUtil = new FileIOUtil(appConfigInstantiator);
			}
			if (glyphLoadingHook == null) {
				glyphLoadingHook = new SVGGlyphLoadingApplicationLifecycleHook();
			}
			if (recentFilesHook == null) {
				recentFilesHook = new RecentFilesApplicationLifecycleHook();
			}

		}

		protected void registerLifecycleHooks() {
			if (enableLogging) {
				registerLifecycleHook(new InitLoggingApplicationLifecycleHook(appName));
			}
			//load config Mixin
			registerLifecycleHook(new LoadConfigApplicationLifecycleHook(appConfigInstantiator, applicationConfigProvider));
			//save config Mixin
			if (autosaveConfig) {
				registerLifecycleHook(new PreShutdownConfigSyncLifecycleHook());
				registerLifecycleHook(new AutoSaveConfigApplicationLifecycleHook(applicationConfigProvider));
			}
			//load icon Mixin
			registerLifecycleHook(glyphLoadingHook);
			//remember recent files
			registerLifecycleHook(recentFilesHook);
		}

		public B uiModel(IUiModel uiModel) {
			return set(()-> this.uiModel = uiModel);
		}

		public B appName(String appName) {
			return set(() -> this.appName = appName);
		}

		public B disableAutosaveConfig() {
			return set(()-> this.autosaveConfig = false);
		}

		public B disableLogging() {
			return set(() -> this.enableLogging = false);
		}

		public B disableRememberOpenedFiles() {
			return set(() -> this.rememberOpenedFiles = false);
		}

		public B customAppConfigProvider(ApplicationConfigProvider applicationConfigProvider) {
			return set(() -> this.applicationConfigProvider = applicationConfigProvider);
		}

		public B customAppConfigInstantiator(ApplicationConfigInstantiator appConfigInstantiator) {
			return set(() -> this.appConfigInstantiator = appConfigInstantiator);
		}

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
	 * Builds the ApplicationOption and then run the lifecycle phases up through {@link LifecyclePhase#POST_INIT}
	 * @param <B> the type of the application options
	 */
	public static class AutoStartBuilder<B extends Builder> extends Builder<B> {
		@Override
		public ApplicationOptions build() throws CoreException {
			ApplicationOptions options = super.build();
			options.startup();
			return options;
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
