package com.legyver.fenxlib.config;

import com.legyver.fenxlib.files.FileIOUtil;

import java.io.File;
import java.io.IOException;

public class ApplicationConfigHandler<T extends ApplicationConfig> {
	private final FileIOUtil fileIoUtil;
	private final ApplicationConfigInstantiator<T> instantiator;
	private T config;

	public ApplicationConfigHandler(FileIOUtil fileIoUtil, ApplicationConfigInstantiator<T> instantiator) {
		this.fileIoUtil = fileIoUtil;
		this.instantiator = instantiator;
	}
	
	public void loadOrInitConfig(String configAsString) throws IOException, IllegalAccessException {
		if (config == null) {
			config = (T) fileIoUtil.readModel(configAsString);
		}
	}
	
	public T getConfig() {
		return config;
	}

	public void saveConfig(File configFile) throws IOException, IllegalAccessException {
		if (config != null) {
			if (!configFile.exists()) {
				configFile.createNewFile();
			}
			fileIoUtil.saveModel(config, configFile);
		}
	}
}
