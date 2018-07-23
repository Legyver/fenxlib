package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.ApplicationConfig;
import com.legyver.fenxlib.config.ApplicationConfigHandler;
import com.legyver.fenxlib.config.parts.ILastOpened;
import com.legyver.fenxlib.config.parts.IRecentlyModified;
import com.legyver.fenxlib.uimodel.WorkingFileAware;
import com.legyver.fenxlib.config.parts.RecentlyViewedFile;
import com.legyver.fenxlib.factory.menu.file.WorkingFileConfig;
import com.legyver.fenxlib.uimodel.DefaultFileOptions;
import com.legyver.fenxlib.uimodel.FileOptions;
import com.legyver.fenxlib.uimodel.RecentFileAware;
import com.legyver.fenxlib.util.hook.LifecycleHook;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
	 * @param primaryStage
	 * @param uiModel
	 * @param handler: the class responsible for loading/saving/accessing your IApplicationConfig
	 */
	public FileBasedApplicationOptions(Stage primaryStage, T uiModel, ApplicationConfigHandler handler) throws IOException, IllegalAccessException {
		super(primaryStage, uiModel);
		firePreInit();
		handler.loadOrInitConfig();
		applicationConfig = handler.getConfig();
		initWorkingFileConfig(getUiModel().getWorkingFileOptions());
		initRecentlyModified(getUiModel());
		registerHook(LifecycleHook.PRE_SHUTDOWN, () -> {
			if (workingFileConfig.getInitialDirectory() != null) {
				ILastOpened lastOpened = applicationConfig.getLastOpened();
				lastOpened.setLastDirectory(workingFileConfig.getInitialDirectory().getAbsolutePath());
				try {
					handler.saveConfig();
				} catch (IOException|IllegalAccessException  ex) {
					System.out.println(ex);//FIXME
				}
			}
		});
	}
	
	/**
	 * 
	 * @param configDirName: The name of the directory where you want the config files stored.  It will end up in {user.home}/<YOUR_CONFIG_DIR_NAME>
	 * @param primaryStage
	 * @param uiModel
	 */
	public FileBasedApplicationOptions(String configDirName, ApplicationConfigInstantiator instantiator, Stage primaryStage, T uiModel) throws IOException, IllegalAccessException {
		this(primaryStage, uiModel, new ApplicationConfigHandler(configDirName, new FileIOUtil(), instantiator));
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
		ILastOpened lastOpened = applicationConfig.getLastOpened();
		String lastDir = lastOpened.getLastDirectory();
		if (lastDir != null) {
			File file = new File(lastDir);
			if (file.exists() || !validateFileExistence) {
				workingDir = file;
			}
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
		workingFileConfig.initialFileName().bind(workingFile.filePathProperty());
		workingFileConfig.initialFileName().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null && !newValue.equalsIgnoreCase(oldValue)) {
					IRecentlyModified recentConfig = applicationConfig.getRecentlyModified();
					if (recentConfig.getValues().stream().anyMatch(m -> newValue.equalsIgnoreCase(m.getPath()))) {
						RecentlyViewedFile recentValue = RecentlyViewedFile.parse(newValue);
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

	protected void setFileValidateExistence(boolean  validateExistence) {
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
