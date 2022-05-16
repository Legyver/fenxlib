package com.legyver.fenxlib.api.lifecycle.hooks;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.config.FileAwareApplicationConfig;
import com.legyver.fenxlib.api.config.parts.ILastOpened;
import com.legyver.fenxlib.api.config.parts.IRecentlyModified;
import com.legyver.fenxlib.api.config.parts.IRecentlyViewedFile;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.DefaultFileOptions;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.files.FileRegistry;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.uimodel.IUiModel;
import com.legyver.fenxlib.api.uimodel.RecentFileAware;
import com.legyver.fenxlib.api.config.ConfigServiceRegistry;
import com.legyver.fenxlib.api.logging.LazyLog;
import com.legyver.fenxlib.api.config.parts.util.RecentlyViewedFileComparator;
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
		FileAwareApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
		IUiModel uiModel = ApplicationContext.getUiModel();
		openingFileUpdatesConfig(applicationConfig);
		initializeDefaultFileBrowseLocation(applicationConfig);
		if (uiModel instanceof RecentFileAware) {
			initRecentlyModified(applicationConfig, (RecentFileAware) uiModel);
		}
	}

	private void initializeDefaultFileBrowseLocation(FileAwareApplicationConfig applicationConfig) throws CoreException {
		File browseTo = getLastModifiedParentDir(applicationConfig);
		Fenxlib.setDefaultBrowseLocation(browseTo);
	}

	/**
	 * Get the parent directory of the last modified file.
	 * @param applicationConfig the application config to read the last modified file from
	 * @return the parent directory if there is a last-opened file and the directory still exists
	 * @throws CoreException if there is an error reading the last-opened file from the config
	 */
	public File getLastModifiedParentDir(FileAwareApplicationConfig applicationConfig) throws CoreException {
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

	private void initRecentlyModified(FileAwareApplicationConfig applicationConfig, RecentFileAware uiModel) throws CoreException {
		List<FileOptions> recentFiles = uiModel.getRecentFiles();
		IRecentlyModified recentlyModified = applicationConfig.getRecentlyModified();
		int limit = recentlyModified.getLimit();
		List<IRecentlyViewedFile> recentlyViewedValues = recentlyModified.getValues();
		recentlyViewedValues.sort(new RecentlyViewedFileComparator());
		for (IRecentlyViewedFile info: recentlyViewedValues) {
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

	private void openingFileUpdatesConfig(FileAwareApplicationConfig applicationConfig) {
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
							IRecentlyViewedFile recentValue = ConfigServiceRegistry.getInstance().adaptRecentlyViewedFile(file);
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
