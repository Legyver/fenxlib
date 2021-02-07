package com.legyver.fenxlib.core.impl.config;

import com.legyver.fenxlib.core.impl.files.FileIOUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ApplicationConfigHandler<T extends ApplicationConfig> {
	private final FileIOUtil fileIoUtil;
	private final ApplicationConfigInstantiator<T> instantiator;
	private T config;

	public ApplicationConfigHandler(FileIOUtil fileIoUtil, ApplicationConfigInstantiator<T> instantiator) {
		this.fileIoUtil = fileIoUtil;
		this.instantiator = instantiator;
	}
	
	public T loadOrInitConfig(String configAsString) throws IOException, IllegalAccessException {
		if (config == null) {
			config = (T) fileIoUtil.readModel(configAsString);
		}
		if (config == null) {
			instantiator.init(new HashMap());
		}
		return config;
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
