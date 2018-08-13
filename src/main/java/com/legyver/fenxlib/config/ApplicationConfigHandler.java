package com.legyver.fenxlib.config;

import com.legyver.fenxlib.util.ApplicationConfigInstantiator;
import com.legyver.fenxlib.util.FileIOUtil;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ApplicationConfigHandler<T extends ApplicationConfig> {
	private final String userHome;
	private final String configDirName;
	private final String configFileName;
	private final FileIOUtil fileIoUtil;
	private final ApplicationConfigInstantiator<T> instantiator;
	private File configFile;
	private T config;

	/**
	 * @param appName
	 */
	public ApplicationConfigHandler(String appName, FileIOUtil fileIoUtil, ApplicationConfigInstantiator<T> instantiator) {
		userHome = System.getProperty("user.home");
		configDirName = userHome + File.separator + appName;
		configFileName = configDirName + File.separator + "config.json";
		this.fileIoUtil = fileIoUtil;
		this.instantiator = instantiator;
	}
	
	public void loadOrInitConfig() throws IOException, IllegalAccessException {
		if (config == null) {
			configFile = new File(configFileName);
			if (configFile.exists()) {
				config = (T) fileIoUtil.readModel(configFile);
			} else {
				File configDir = new File(configDirName);
				if (!configDir.exists()) {
					configDir.mkdirs();
				}
				configFile.createNewFile();
				config = instantiator.init(new LinkedHashMap());
			}
		}
	}
	
	public T getConfig() {
		return config;
	}

	public void saveConfig() throws IOException, IllegalAccessException {
		if (config != null) {
			fileIoUtil.saveModel(config, configFile);
		}
	}
}
