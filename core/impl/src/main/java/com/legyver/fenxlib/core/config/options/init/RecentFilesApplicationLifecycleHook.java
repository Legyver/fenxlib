package com.legyver.fenxlib.core.config.options.init;

import com.legyver.fenxlib.core.config.ApplicationConfig;
import com.legyver.fenxlib.core.config.IRecentlyViewedFile;
import com.legyver.fenxlib.core.config.parts.IRecentlyModified;
import com.legyver.fenxlib.core.config.parts.RecentlyViewedFile;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.factory.menu.file.DefaultFileBrowseLocation;
import com.legyver.fenxlib.core.files.FileRegistry;
import com.legyver.fenxlib.core.uimodel.DefaultFileOptions;
import com.legyver.fenxlib.core.uimodel.FileOptions;
import com.legyver.fenxlib.core.uimodel.IUiModel;
import com.legyver.fenxlib.core.uimodel.RecentFileAware;
import com.legyver.fenxlib.core.util.RecentlyViewedFileComparator;
import com.legyver.fenxlib.core.util.hook.ExecutableHook;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;
import com.legyver.util.nippe.Base;
import com.legyver.util.nippe.Step;
import javafx.beans.property.StringProperty;
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

	private void setupFileOptions() {
		ApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
		IUiModel uiModel = ApplicationContext.getUiModel();
		openingFileUpdatesConfig(applicationConfig);
		initializeDefaultFileBrowseLocation(applicationConfig);
		if (uiModel instanceof RecentFileAware) {
			initRecentlyModified(applicationConfig, (RecentFileAware) uiModel);
		}
	}

	private void initializeDefaultFileBrowseLocation(ApplicationConfig applicationConfig) {
		DefaultFileBrowseLocation defaultFileBrowseLocation = ApplicationContext.getFileRegistry().getDefaultFileBrowseLocation();
		defaultFileBrowseLocation.setInitialDirectory(getLastModifiedParentDir(applicationConfig));
	}

	public File getLastModifiedParentDir(ApplicationConfig applicationConfig) {
		File workingDir = null;
		File file = new Step<>(new Step<>(new Base<>(applicationConfig.getLastOpened()),
				(last) -> last.getLastFile()),
				(dir) -> new File(dir)).execute();
		if (isFileValid(file)) {
			workingDir = file;
		}

		return workingDir;
	}

	private void initRecentlyModified(ApplicationConfig applicationConfig, RecentFileAware uiModel) {
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

	private void openingFileUpdatesConfig(ApplicationConfig applicationConfig) {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		ObservableList<FileOptions> openFiles = fileRegistry.getOpenFiles();

		//update the timestamp on the config whenever a file is opened
		openFiles.addListener((ListChangeListener<FileOptions>) change -> {
			if (change.next() && change.wasAdded()) {
				List<? extends FileOptions> workingFileConfigList = change.getAddedSubList();
				for (int i = 0; i < workingFileConfigList.size(); i++) {
					FileOptions workingFile = workingFileConfigList.get(i);
					IRecentlyModified recentConfig = applicationConfig.getRecentlyModified();
					String workingFilePath = workingFile.getFilePath();
					List<IRecentlyViewedFile> recentlyViewedFiles = recentConfig.getValues();
					Optional<IRecentlyViewedFile> option = recentlyViewedFiles.stream()
							.filter(m -> {
								return workingFilePath.equalsIgnoreCase(m.getPath());
							})
							.findFirst();
					if (option.isPresent()) {//update the timestamp
						IRecentlyViewedFile recentValue = option.get();
						recentValue.setLastAccessed(LocalDateTime.now());
					} else {
						File file = workingFile.getFile();
						RecentlyViewedFile recentValue = RecentlyViewedFile.parse(file);
						recentConfig.addValue(recentValue);
					}
				}
			}
		});
	}



}
