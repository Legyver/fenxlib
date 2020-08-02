package com.legyver.fenxlib.core.config.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.config.ApplicationConfigHandler;
import com.legyver.fenxlib.core.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.core.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.config.load.ApplicationHome;
import com.legyver.fenxlib.core.config.options.mixins.*;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.files.FileIOUtil;
import com.legyver.fenxlib.core.uimodel.IUiModel;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;

public class ApplicationOptions {
	private final IUiModel uiModel;

	private ApplicationOptions(IUiModel uiModel) {
		this.uiModel = uiModel;
	}

	public void autostart() throws CoreException {
		bootstrap();
		preInit();
		init();
	}

	public void bootstrap() throws CoreException {
		ApplicationContext.setUiModel(uiModel);
		executePhase(LifecyclePhase.BOOTSTRAP);
	}

	public void preInit() throws CoreException {
		executePhase(LifecyclePhase.PRE_INIT);
	}

	public void init() throws CoreException {
		executePhase(LifecyclePhase.INIT);
	}

	protected void executePhase(LifecyclePhase phase) throws CoreException {
		ApplicationContext.getApplicationLifecycleHookRegistry().executeHook(phase);
	}

	public static class Builder<B extends Builder> implements IApplicationOptionsBuilder<B> {
		protected IUiModel uiModel;
		protected ApplicationConfigProvider applicationConfigProvider;
		protected ApplicationConfigHandler appConfigHandler;
		protected ApplicationConfigInstantiator appConfigInstantiator;
		protected FileIOUtil fileIOUtil;
		protected SVGGlyphLoadingMixin glyphLoadingMixin;
		protected RecentFilesMixin recentFilesMixin;
		protected String appName;
		protected boolean autosaveConfig = true;
		protected boolean enableLogging = true;
		protected boolean rememberOpenedFiles = true;

		public ApplicationOptions build() throws CoreException {
			validate();
			defaultUnspecified();
			registerLifecycleHooks();
			return new ApplicationOptions(uiModel);
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
				fileIOUtil = new FileIOUtil();
			}
			if (appConfigHandler == null) {
				appConfigHandler = new ApplicationConfigHandler(fileIOUtil, appConfigInstantiator);
			}
			if (glyphLoadingMixin == null) {
				glyphLoadingMixin = new IcoMoonSvgMixin();
			}
			if (recentFilesMixin == null) {
				recentFilesMixin = new RecentFilesMixin();
			}

		}

		protected void registerLifecycleHooks() {
			if (enableLogging) {
				mixinLifecycleHook(new InitLoggingMixin(appName));
			}
			//load config Mixin
			mixinLifecycleHook(new LoadConfigMixin(appConfigHandler, applicationConfigProvider));
			//save config Mixin
			if (autosaveConfig) {
				mixinLifecycleHook(new AutoSaveConfigMixin(applicationConfigProvider, appConfigHandler));
			}
			//load icon Mixin
			mixinLifecycleHook(glyphLoadingMixin);
			//remember recent files
			mixinLifecycleHook(recentFilesMixin);
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

		public B customAppConfigHandler(ApplicationConfigHandler applicationConfigHandler) {
			return set(() -> this.appConfigHandler = applicationConfigHandler);
		}

		public B customAppConfigInstantiator(ApplicationConfigInstantiator appConfigInstantiator) {
			return set(() -> this.appConfigInstantiator = appConfigInstantiator);
		}

		public B customFileIOUtil(FileIOUtil fileIOUtil) {
			return set(()-> this.fileIOUtil = fileIOUtil);
		}

		public B customSvgLoader(SVGGlyphLoadingMixin glyphLoadingMixin) {
			return set(() -> this.glyphLoadingMixin = glyphLoadingMixin);
		}

		@Override
		public B mixinLifecycleHook(HookRegistrationMixin hookRegistrationMixin) {
			return set(()-> ApplicationContext.getApplicationLifecycleHookRegistry()
					.registerHook(hookRegistrationMixin.getLifecyclePhase(), hookRegistrationMixin.getExecutableHook(), hookRegistrationMixin.getPriority()));
		}

		protected B set(NoArgFunction noArgFunction) {
			noArgFunction.execute();
			return (B) this;
		}

	}

	public static class AutoStartBuilder<B extends Builder> extends Builder<B> {
		@Override
		public ApplicationOptions build() throws CoreException {
			ApplicationOptions options = super.build();
			options.autostart();
			return options;
		}
	}

	@FunctionalInterface
	public  interface NoArgFunction {
		void execute();
	}

}
