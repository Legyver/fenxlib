package com.legyver.fenxlib.config.json;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.*;
import com.legyver.fenxlib.api.config.adapter.ConfigAdapters;
import com.legyver.fenxlib.api.config.adapter.IConfigAdapter;
import com.legyver.fenxlib.config.json.adapter.RecentlyViewedFileJsonAdapter;
import com.legyver.fenxlib.config.json.util.JsonFileIOUtil;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionActionDecorator;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionVoidActionDecorator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads the application config from file.
 * This was moved to a service to make tests more sensible.
 */
public class ConfigServiceImpl<T extends JsonApplicationConfig> implements ConfigService<ApplicationConfigInstantiator, T> {
	private JsonFileIOUtil fileIOUtil;
	private static final Map<String, IConfigAdapter> adapters = new HashMap<>();
	static {
		adapters.put(ConfigAdapters.RECENTLY_VIEWED_FILE.name(), new RecentlyViewedFileJsonAdapter());
	}

	/**
	 * Construct a config service that loads the config from file
	 */
	public ConfigServiceImpl() {
		this.fileIOUtil = new JsonFileIOUtil();
	}

	@Override
	public T loadConfig(String filename) throws CoreException {
		return (T) new ExceptionToCoreExceptionActionDecorator<>(
				() -> fileIOUtil.readModel(new File(filename))
		).execute();
	}

	@Override
	public boolean saveConfig(String filename, JsonApplicationConfig config) throws CoreException {
		config.sync();
		new ExceptionToCoreExceptionVoidActionDecorator(
				() -> fileIOUtil.saveModel(config, new File(filename))
		).execute();
		return true;
	}

	@Override
	public IConfigAdapter getAdapter(String forName) {
		return adapters.get(forName);
	}

	@Override
	public int order() {
		return 10;
	}

	@Override
	public void init(ApplicationConfigInstantiator initializer) {
		fileIOUtil = new JsonFileIOUtil(initializer);
	}
}
