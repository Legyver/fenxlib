package com.legyver.fenxlib.config.options;

import com.legyver.fenxlib.config.*;
import com.legyver.fenxlib.config.load.AppConfigProvider;
import com.legyver.fenxlib.config.ApplicationConfig;
import com.legyver.fenxlib.config.load.AppHome;
import com.legyver.fenxlib.config.parts.ILastOpened;
import com.legyver.fenxlib.config.parts.IRecentlyModified;
import com.legyver.fenxlib.config.parts.RecentlyViewedFile;
import com.legyver.fenxlib.factory.menu.file.WorkingFileConfig;
import com.legyver.fenxlib.files.FileIOUtil;
import com.legyver.fenxlib.uimodel.DefaultFileOptions;
import com.legyver.fenxlib.uimodel.FileOptions;
import com.legyver.fenxlib.uimodel.RecentFileAware;
import com.legyver.fenxlib.util.RecentlyViewedFileComparator;
import com.legyver.fenxlib.util.hook.LifecycleHook;
import com.legyver.util.nippe.Base;
import com.legyver.util.nippe.Step;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

/**
 * Tracks modified files to populate a list of Recently Modified files
 * Stores config
 */
public class FileBasedApplicationOptions<T extends RecentFileAware> extends DefaultApplicationOptions<T> {
	private final ApplicationConfig applicationConfig;
	private final WorkingFileConfig workingFileConfig = new WorkingFileConfig();
	private boolean validateFileExistence = true;
	
	/**
	 *
	 * @param appConfigProvider
	 * @param primaryStage
	 * @param uiModel
	 * @param handler: the class responsible for loading/saving/accessing your IApplicationConfig
	 */
	public FileBasedApplicationOptions(AppConfigProvider appConfigProvider, Stage primaryStage, T uiModel, ApplicationConfigHandler handler) throws IOException, IllegalAccessException {
		super(appConfigProvider, primaryStage, uiModel);
		firePreInit();
		handler.loadOrInitConfig(appConfigProvider.getApplicationConfigAsString());
		applicationConfig = handler.getConfig();
		initWorkingFileConfig(getUiModel().getWorkingFileOptions());
		initRecentlyModified(getUiModel());
		registerHook(LifecycleHook.PRE_SHUTDOWN, () -> {
			if (workingFileConfig.getInitialDirectory() != null) {
				ILastOpened lastOpened = applicationConfig.getLastOpened();
				lastOpened.setLastDirectory(workingFileConfig.getInitialDirectory().getAbsolutePath());
			}
			try {
				applicationConfig.sync();
				handler.saveConfig(appConfigProvider.getApplicationConfigFile());
			} catch (IOException|IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		});
	}

	public FileBasedApplicationOptions(String appName, Stage primaryStage, T uiModel, ApplicationConfigHandler handler) throws IOException, IllegalAccessException {
		this(new AppHome(appName), primaryStage, uiModel, handler);
	}

		/**
		 *
		 * @param appName: The name of the directory where you want the config files stored.  It will end up in either {user.home}/<YOUR_CONFIG_DIR_NAME> or %APPDATA%\<YOUR_CONFIG_DIR_NAME>
		 * @param primaryStage
		 * @param uiModel
		 */
	public FileBasedApplicationOptions(String appName, ApplicationConfigInstantiator instantiator, Stage primaryStage, T uiModel) throws IOException, IllegalAccessException {
		this(appName, primaryStage, uiModel, new ApplicationConfigHandler(new FileIOUtil(instantiator), instantiator));
	}

	public File getDefaultWorkingDir() {
		File workingDir = getLastModifiedParentDir();
		if (workingDir == null) {
			workingDir = getDocumentsDir();
		}
		if (workingDir == null) {
			workingDir = getUserHomeDir();
		}
		return workingDir;
	}

	public File getLastModifiedParentDir() {
		File workingDir = null;
		File file = new Step<>(new Step<>(new Base<>(applicationConfig.getLastOpened()),
				last -> last.getLastDirectory()),
				dir -> new File(dir)
		).execute();

		if ((file != null && file.exists()) || !validateFileExistence) {
			workingDir = file;
		}
		return workingDir;
	}

	public File getDocumentsDir() {
		File documents = new File(System.getProperty("user.home") + File.separator + "documents");
		if (documents.exists()) {
			return documents;
		}
		return null;
	}

	public File getUserHomeDir() {
		return new File(System.getProperty("user.home"));
	}

	private void initWorkingFileConfig(FileOptions workingFile) {
		workingFileConfig.initialFileName().bind(workingFile.fileNameProperty());
		workingFile.fileNameProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					IRecentlyModified recentConfig = applicationConfig.getRecentlyModified();
					Optional<RecentlyViewedFile> option = recentConfig.getValues().stream()
							.filter(m -> {
								return newValue.equalsIgnoreCase(m.getName());
							})
							.findFirst();
					if (option.isPresent()) {//update the timestamp
						RecentlyViewedFile recentValue = option.get();
						recentValue.setLastAccessed(LocalDateTime.now());
					} else {
						File file = workingFile.getFile();
						RecentlyViewedFile recentValue = RecentlyViewedFile.parse(file);
						recentConfig.addValue(recentValue);
					}
				}
			}
		});

		File workingDir = getDefaultWorkingDir();
		if (workingDir != null) {
			workingFileConfig.setInitialDirectory(workingDir);
		}
	}

	public WorkingFileConfig getWorkingFileConfig() throws IOException {
		return workingFileConfig;
	}

	public ApplicationConfig getApplicationConfig() {
		return applicationConfig;
	}

	protected void setFileValidateExistence(boolean validateExistence) {
		this.validateFileExistence = validateExistence;
	}

	protected void firePreInit() {
		//template
	}

	private void initRecentlyModified(T uiModel) {
		List<FileOptions> recentFiles = uiModel.getRecentFiles();
		IRecentlyModified recentlyModified = applicationConfig.getRecentlyModified();
		int limit = recentlyModified.getLimit();
		List<RecentlyViewedFile> recentlyViewedValues = recentlyModified.getValues();
		recentlyViewedValues.sort(new RecentlyViewedFileComparator());
		for (RecentlyViewedFile info: recentlyViewedValues) {
			File file = new File(info.getPath());
			if (file.exists() || !validateFileExistence) {
				DefaultFileOptions recentFileOptions = new DefaultFileOptions();
				recentFileOptions.setFile(file);
				recentFileOptions.setFileName(info.getName());
				recentFileOptions.setFilePath(file.getAbsolutePath());
				recentFiles.add(recentFileOptions);
				if (recentFiles.size() == limit) {
					break;
				}
			}
		}
	}
}
