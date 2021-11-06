package com.legyver.fenxlib.core.impl.config;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.ConfigService;
import com.legyver.fenxlib.core.api.config.IApplicationConfig;
import com.legyver.fenxlib.core.impl.files.FileIOUtil;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionActionDecorator;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionVoidActionDecorator;

import java.io.File;

/**
 * Loads the application config from file.
 * This was moved to a service to make tests more sensible.
 */
public class ConfigServiceImpl implements ConfigService {
	private final FileIOUtil fileIOUtil;

	/**
	 * Construct a config service that loads the config from file
	 */
	public ConfigServiceImpl() {
		this.fileIOUtil = new FileIOUtil();
	}

	@Override
	public <T extends IApplicationConfig> T loadConfig(String filename) throws CoreException {
		return (T) new ExceptionToCoreExceptionActionDecorator<>(
				() -> fileIOUtil.readModel(new File(filename))
		).execute();
	}

	@Override
	public <T extends IApplicationConfig> boolean saveConfig(String filename, T config) throws CoreException {
		new ExceptionToCoreExceptionVoidActionDecorator(
				() -> fileIOUtil.saveModel(config, new File(filename))
		).execute();
		return true;
	}

	@Override
	public int order() {
		return 10;
	}
}
