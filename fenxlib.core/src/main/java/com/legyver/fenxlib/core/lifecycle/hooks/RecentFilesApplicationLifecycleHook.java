package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.api.config.ConfigServiceRegistry;
import com.legyver.fenxlib.api.config.parts.LastOpened;
import com.legyver.fenxlib.api.config.parts.RecentFile;
import com.legyver.fenxlib.api.config.parts.RecentFiles;
import com.legyver.fenxlib.api.config.parts.util.RecentlyViewedFileComparator;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.DefaultFileOptions;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.files.FileRegistry;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.api.logging.LazyLog;
import com.legyver.fenxlib.api.uimodel.IUiModel;
import com.legyver.fenxlib.api.uimodel.RecentFileAware;
import com.legyver.fenxlib.core.config.CoreConfigSection;
import com.legyver.fenxlib.core.config.ICoreApplicationConfig;
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

	@Override
	public String getName() {
		return RecentFilesApplicationLifecycleHook.class.getSimpleName();
	}

	private void setupFileOptions() throws CoreException {
		ApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
		IUiModel uiModel = ApplicationContext.getUiModel();
		if (applicationConfig instanceof ICoreApplicationConfig) {
			CoreConfigSection configSection = ((ICoreApplicationConfig) applicationConfig).getCoreConfig();
			if (configSection == null) {
				configSection = new CoreConfigSection();
				((ICoreApplicationConfig) applicationConfig).setCoreConfig(configSection);
			}
			openingFileUpdatesConfig(configSection);
			initializeDefaultFileBrowseLocation(configSection);
			if (uiModel instanceof RecentFileAware) {
				initRecentlyModified(configSection, (RecentFileAware) uiModel);
			}
		}
	}

	private void initializeDefaultFileBrowseLocation(CoreConfigSection configSection) throws CoreException {
		File browseTo = getLastModifiedParentDir(configSection);
		Fenxlib.setDefaultBrowseLocation(browseTo);
	}

	/**
	 * Get the parent directory of the last modified file.
	 * @param configSection the application config to read the last modified file from
	 * @return the parent directory if there is a last-opened file and the directory still exists
	 * @throws CoreException if there is an error reading the last-opened file from the config
	 */
	public File getLastModifiedParentDir(CoreConfigSection configSection) throws CoreException {
		File workingDir = null;
		LastOpened lastOpened = configSection.getLastOpened();
		File parentDir = CoreException.unwrap(() -> new Step<>(new Step<>(new Step<>(new Base<>(lastOpened),
				(last) -> CoreException.wrap(() -> last.getFilePath())),
				(filename) -> new File(filename)),
				(f) -> f.getParentFile()
				).execute());

		if (isDirectoryValid(parentDir)) {
			workingDir = parentDir;
		}

		return workingDir;
	}

	private void initRecentlyModified(CoreConfigSection configSection, RecentFileAware uiModel) throws CoreException {
		List<FileOptions> recentFiles = uiModel.getRecentFiles();
		RecentFiles recentlyModified = configSection.getRecentFiles();
		int limit = recentlyModified.getLimit();
		List<RecentFile> recentlyViewedValues = recentlyModified.getValues();
		recentlyViewedValues.sort(new RecentlyViewedFileComparator());
		for (RecentFile info: recentlyViewedValues) {
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

	/**
	 * Null-safe check for if a file exists.
	 * @param file the file candidate
	 * @return true if the file is not null and exists
	 */
	protected boolean isFileValid(File file) {
		return file != null && file.exists();
	}

	/**
	 * Null-safe check if the directory is valid
	 * @param directory the candidate directory
	 * @return true if the directory is not null and is in fact a directory
	 */
	protected boolean isDirectoryValid(File directory) {
		return directory != null && directory.isDirectory();
	}

	private void openingFileUpdatesConfig(CoreConfigSection configSection) {
		FileRegistry fileRegistry = ApplicationContext.getFileRegistry();
		ObservableList<FileOptions> openFiles = fileRegistry.getOpenFiles();

		//update the timestamp on the config whenever a file is opened
		openFiles.addListener((ListChangeListener<FileOptions>) change -> {
			if (change.next() && change.wasAdded()) {
				try {
					List<? extends FileOptions> workingFileConfigList = change.getAddedSubList();
					for (int i = 0; i < workingFileConfigList.size(); i++) {
						FileOptions workingFile = workingFileConfigList.get(i);
						RecentFiles recentConfig = configSection.getRecentFiles();
						String workingFilePath = workingFile.getFilePath();
						List<RecentFile> recentlyViewedFiles = recentConfig.getValues();
						Optional<RecentFile> option = CoreException.unwrap(() -> recentlyViewedFiles.stream()
								.filter(m -> CoreException.wrap(
										() -> workingFilePath.equalsIgnoreCase(m.getPath())))
								.findFirst());
						if (option.isPresent()) {//update the timestamp
							RecentFile recentValue = option.get();
							recentValue.setLastAccessed(LocalDateTime.now());
						} else {
							File file = workingFile.getFile();
							RecentFile recentValue = ConfigServiceRegistry.getInstance().adaptRecentlyViewedFile(file);
							recentConfig.addRecentFile(recentValue);
						}
					}
				} catch (CoreException ex) {
					logger.error("Error updating recently viewed file: " + ex.getMessage(), ex);
				}
			}
		});
	}

}
