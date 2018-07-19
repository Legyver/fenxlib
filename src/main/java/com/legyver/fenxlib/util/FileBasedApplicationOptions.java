package com.legyver.fenxlib.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.legyver.fenxlib.config.ApplicationConfig;
import com.legyver.fenxlib.config.ApplicationConfigHandler;
import com.legyver.fenxlib.config.parts.LastOpened;
import com.legyver.fenxlib.uimodel.RecentFileAware;
import com.legyver.fenxlib.config.parts.RecentlyViewedFile;
import com.legyver.fenxlib.config.parts.RecentlyModified;
import com.legyver.fenxlib.factory.menu.file.WorkingFileConfig;
import com.legyver.fenxlib.uimodel.FileOptions;
import com.legyver.fenxlib.util.hook.LifecycleHook;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

	/**
	 * 
	 * @param primaryStage
	 * @param uiModel
	 * @param handler: the class responsible for loading/saving/accessing your IApplicationConfig
	 */
	public FileBasedApplicationOptions(Stage primaryStage, T uiModel, ApplicationConfigHandler handler) throws IOException, IllegalAccessException {
		super(primaryStage, uiModel);
		handler.loadOrInitConfig();
		applicationConfig = handler.getConfig();
		initWorkingFileConfig(getUiModel().getFileOptions());
		registerHook(LifecycleHook.PRE_SHUTDOWN, () -> {
			if (workingFileConfig.getInitialDirectory() != null) {
				LastOpened lastOpened = applicationConfig.getLastOpened();
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
	 * @param type: The TypeReference based on the class of your config file
	 * @param primaryStage
	 * @param uiModel
	 */
	public FileBasedApplicationOptions(String configDirName, TypeReference type, Stage primaryStage, T uiModel) throws IOException, IllegalAccessException {
		this(primaryStage, uiModel, new ApplicationConfigHandler(configDirName, new FileIOUtil(), type));
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
		LastOpened lastOpened = applicationConfig.getLastOpened();
		if (lastOpened == null) {
			lastOpened = new LastOpened();
			applicationConfig.setLastOpened(lastOpened);
		}
		String lastDir = lastOpened.getLastDirectory();
		if (lastDir != null) {
			File file = new File(lastDir);
			if (file.exists()) {
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
					RecentlyModified recentConfig = applicationConfig.getRecentlyModified();
					if (recentConfig == null) {
						recentConfig = new RecentlyModified();
						applicationConfig.setRecentlyModified(null);
					}
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

}
