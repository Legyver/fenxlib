package com.legyver.fenxlib.core.config.options.mixins;

import com.legyver.fenxlib.core.config.ApplicationConfig;
import com.legyver.fenxlib.core.config.ApplicationConfigHandler;
import com.legyver.fenxlib.core.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.core.config.parts.ILastOpened;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.files.FileRegistry;
import com.legyver.fenxlib.core.uimodel.FileOptions;
import com.legyver.fenxlib.core.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;
import com.legyver.util.adaptex.ExceptionToCoreExceptionConsumerDecorator;
import javafx.collections.ObservableList;

import java.io.File;

public class AutoSaveConfigMixin implements HookRegistrationMixin {
	private final ApplicationConfigProvider appConfigProvider;
	private final ApplicationConfigHandler applicationConfigHandler;

	public AutoSaveConfigMixin(ApplicationConfigProvider applicationConfigProvider, ApplicationConfigHandler applicationConfigHandler) {
		this.appConfigProvider = applicationConfigProvider;
		this.applicationConfigHandler = applicationConfigHandler;
	}

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_SHUTDOWN;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			ApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
			updateLastOpenedDirectory(applicationConfig);
			saveFile(applicationConfig);
			return;
		};
	}

	private void saveFile(ApplicationConfig applicationConfig) throws com.legyver.core.exception.CoreException {
		new ExceptionToCoreExceptionConsumerDecorator<>((File file) -> {
			applicationConfig.sync();
			applicationConfigHandler.saveConfig(file);
		}).accept(appConfigProvider.getApplicationConfigFile());
	}

	private void updateLastOpenedDirectory(ApplicationConfig applicationConfig) {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		ObservableList<FileOptions> openFiles = fileRegistry.getOpenFiles();

		if (!openFiles.isEmpty()) {
			ILastOpened lastOpened = applicationConfig.getLastOpened();
			FileOptions mostRecent = openFiles.get(openFiles.size() -1);
			lastOpened.setLastDirectory(mostRecent.getFilePath());
		}
	}
}
