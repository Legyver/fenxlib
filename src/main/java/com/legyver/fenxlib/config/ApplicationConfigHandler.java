package com.legyver.fenxlib.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.legyver.fenxlib.util.FileIOUtil;
import com.legyver.fenxlib.util.JsonFileContext;
import java.io.File;
import java.io.IOException;

public class ApplicationConfigHandler {
	private final String userHome;
	private final String configDirName;
	private final String configFileName;
	private final FileIOUtil fileIoUtil;
	private final TypeReference type;
	
	private File configFile;
	private ApplicationConfig config;

	/**
	 * @param appName
	 * @param type: The Class of your config
	 */
	public ApplicationConfigHandler(String appName, FileIOUtil fileIoUtil, TypeReference type) {
		userHome = System.getProperty("user.home");
		configDirName = userHome + File.separator + appName;
		configFileName = configDirName + File.separator + "config.json";
		this.fileIoUtil = fileIoUtil;
		this.type = type;
	}

	public void loadOrInitConfig() throws IOException, IllegalAccessException {
		if (config == null) {
			File configDir = new File(configDirName);
			if (!configDir.exists()) {
				configDir.mkdirs();
			}
			configFile = new File(configFileName);
			if (configFile.exists()) {
				config = (ApplicationConfig) fileIoUtil.readModel(new JsonFileContext(configFile, type));
			} else {
				configFile.createNewFile();
				config = new ApplicationConfig();
			}
		}
	}
	
	public ApplicationConfig getConfig() {
		return config;
	}

	public void saveConfig() throws IOException, IllegalAccessException {
		if (config != null) {
			fileIoUtil.saveModel(config, new JsonFileContext(configFile, type));
		}
	}
}
