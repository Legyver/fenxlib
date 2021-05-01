package com.legyver.fenxlib.core.impl.config.options.init;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.parts.ILastOpened;
import com.legyver.fenxlib.core.api.uimodel.IUiModel;
import com.legyver.fenxlib.core.api.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.fenxlib.core.api.config.options.init.ApplicationLifecycleHook;
import com.legyver.fenxlib.core.impl.config.ApplicationConfig;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyViewedFile;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyModified;
import com.legyver.fenxlib.core.impl.config.parts.RecentlyViewedFile;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.factory.menu.file.internal.DefaultFileBrowseLocation;
import com.legyver.fenxlib.core.impl.files.FileRegistry;
import com.legyver.fenxlib.core.impl.log.LazyLog;
import com.legyver.fenxlib.core.impl.uimodel.DefaultFileOptions;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;
import com.legyver.fenxlib.core.impl.uimodel.RecentFileAware;
import com.legyver.fenxlib.core.impl.util.RecentlyViewedFileComparator;
import com.legyver.utils.nippe.Base;
import com.legyver.utils.nippe.Step;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Populate recent files menu
 */
public class RecentFilesApplicationLifecycleHook implements ApplicationLifecycleHook {
	private static final LazyLog logger = new LazyLog(RecentFilesApplicationLifecycleHook.class);

	@Override
	public LifecyclePhase getLifecyclePhase() {
		return LifecyclePhase.PRE_INIT;
	}

	@Override
	public ExecutableHook getExecutableHook() {
		return () -> {
			setupFileOptions();
		};
	}

	@Override
	public int getPriority() {
		return 1000;
	}

	private void setupFileOptions() throws CoreException {
		ApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
		IUiModel uiModel = ApplicationContext.getUiModel();
		openingFileUpdatesConfig(applicationConfig);
		initializeDefaultFileBrowseLocation(applicationConfig);
		if (uiModel instanceof RecentFileAware) {
			initRecentlyModified(applicationConfig, (RecentFileAware) uiModel);
		}
	}

	private void initializeDefaultFileBrowseLocation(ApplicationConfig applicationConfig) throws CoreException {
		DefaultFileBrowseLocation defaultFileBrowseLocation = ApplicationContext.getFileRegistry().getDefaultFileBrowseLocation();
		defaultFileBrowseLocation.setInitialDirectory(getLastModifiedParentDir(applicationConfig));
	}

	public File getLastModifiedParentDir(ApplicationConfig applicationConfig) throws CoreException {
		File workingDir = null;
		ILastOpened lastOpened = applicationConfig.getLastOpened();
		File parentDir = CoreException.unwrap(() -> new Step<>(new Step<>(new Step<>(new Base<>(lastOpened),
				(last) -> CoreException.wrap(() -> last.getLastFile())),
				(filename) -> new File(filename)),
				(f) -> f.getParentFile()
				).execute());

		if (isDirectoryValid(parentDir)) {
			workingDir = parentDir;
		}

		return workingDir;
	}

	private void initRecentlyModified(ApplicationConfig applicationConfig, RecentFileAware uiModel) throws CoreException {
		List<FileOptions> recentFiles = uiModel.getRecentFiles();
		IRecentlyModified recentlyModified = applicationConfig.getRecentlyModified();
		int limit = recentlyModified.getLimit();
		List<RecentlyViewedFile> recentlyViewedValues = recentlyModified.getValues();
		recentlyViewedValues.sort(new RecentlyViewedFileComparator());
		for (RecentlyViewedFile info: recentlyViewedValues) {
			File file = new File(info.getPath());
			if (isFileValid(file)) {
				DefaultFileOptions recentFileOptions = new DefaultFileOptions(file, false);
				recentFiles.add(recentFileOptions);
				if (recentFiles.size() == limit) {
					break;
				}
			}
		}
	}

	protected boolean isFileValid(File file) {
		return file != null && file.exists();
	}

	protected boolean isDirectoryValid(File file) {
		return file != null && file.isDirectory();
	}

	private void openingFileUpdatesConfig(ApplicationConfig applicationConfig) {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		ObservableList<FileOptions> openFiles = fileRegistry.getOpenFiles();

		//update the timestamp on the config whenever a file is opened
		openFiles.addListener((ListChangeListener<FileOptions>) change -> {
			if (change.next() && change.wasAdded()) {
				try {
					List<? extends FileOptions> workingFileConfigList = change.getAddedSubList();
					for (int i = 0; i < workingFileConfigList.size(); i++) {
						FileOptions workingFile = workingFileConfigList.get(i);
						IRecentlyModified recentConfig = applicationConfig.getRecentlyModified();
						String workingFilePath = workingFile.getFilePath();
						List<IRecentlyViewedFile> recentlyViewedFiles = recentConfig.getValues();
						Optional<IRecentlyViewedFile> option = CoreException.unwrap(() -> recentlyViewedFiles.stream()
								.filter(m -> CoreException.wrap(
										() -> workingFilePath.equalsIgnoreCase(m.getPath())))
								.findFirst());
						if (option.isPresent()) {//update the timestamp
							IRecentlyViewedFile recentValue = option.get();
							recentValue.setLastAccessed(LocalDateTime.now());
						} else {
							File file = workingFile.getFile();
							RecentlyViewedFile recentValue = RecentlyViewedFile.parse(file);
							recentConfig.addValue(recentValue);
						}
					}
				} catch (CoreException ex) {
					logger.error("Error updating recently viewed file: " + ex.getMessage(), ex);
				}
			}
		});
	}



}
