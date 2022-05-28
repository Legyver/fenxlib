package com.legyver.fenxlib.config.json;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.ConfigService;
import com.legyver.fenxlib.api.config.adapter.ConfigAdapterMap;
import com.legyver.fenxlib.api.config.adapter.ConfigAdapterPartType;
import com.legyver.fenxlib.api.config.adapter.IConfigAdapter;
import com.legyver.fenxlib.api.files.util.ConfigInstantiator;
import com.legyver.fenxlib.api.io.IOServiceRegistry;
import com.legyver.fenxlib.api.io.content.StringContentWrapper;
import com.legyver.fenxlib.config.json.adapter.ConfigFileJsonAdapter;
import com.legyver.fenxlib.config.json.adapter.RecentlyViewedFileJsonAdapter;
import com.legyver.fenxlib.config.json.util.JsonConfigInstantiator;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionActionDecorator;
import com.legyver.utils.jackiso.JacksonObjectMapper;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads the application config from file.
 * This was moved to a service to make tests more sensible.
 */
public class JsonConfigService<T extends JsonApplicationConfig> implements ConfigService<T> {

	private static final ConfigAdapterMap adapters = new ConfigAdapterMap();
	static {
		adapters.put(ConfigAdapterPartType.FULL_FILE, new ConfigFileJsonAdapter());
		adapters.put(ConfigAdapterPartType.RECENTLY_VIEWED_FILE, new RecentlyViewedFileJsonAdapter());
	}

	@Override
	public T loadConfig(String filename) throws CoreException {
		return (T) new ExceptionToCoreExceptionActionDecorator<T>( () -> {
			InputStream temp = IOServiceRegistry.getInstance().loadInputStream(filename, true);
			if (temp == null) {
				throw new CoreException("Unable to load config: " + filename);
			}
			try (InputStream inputStream = temp;
				 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
				Map configAsMap = JacksonObjectMapper.INSTANCE.readValue(bufferedInputStream, Map.class);
				return (T) adapters.get(ConfigAdapterPartType.FULL_FILE).adapt(configAsMap);
			}
		}).execute();
	}

	@Override
	public boolean saveConfig(String filename, JsonApplicationConfig config) throws CoreException {
		config.sync();
		Map map = config.getRawMap();
		String json = JacksonObjectMapper.INSTANCE.writeValueAsStringWithPrettyPrint(map);
		return IOServiceRegistry.getInstance().save(new StringContentWrapper(json), filename, true);
	}

	@Override
	public IConfigAdapter getAdapter(String forName) {
		return adapters.get(forName);
	}

	@Override
	public IConfigAdapter getAdapter(ConfigAdapterPartType partType) {
		return adapters.get(partType);
	}

	@Override
	public int order() {
		return 10;
	}

	@Override
	public void init(ConfigInstantiator initializer) {
		JsonConfigInstantiator<JsonApplicationConfig> jsonConfigInstantiator;
		if (initializer instanceof JsonConfigInstantiator) {
			jsonConfigInstantiator = (JsonConfigInstantiator) initializer;
		} else {
			jsonConfigInstantiator = map -> {
				Object o = initializer.init(map);
				return o instanceof JsonApplicationConfig ? (JsonApplicationConfig) o : null;
			};
		}
		IConfigAdapter adapter = adapters.get(ConfigAdapterPartType.FULL_FILE);
		if (adapter instanceof ConfigFileJsonAdapter) {
			((ConfigFileJsonAdapter) adapter).setJsonConfigInstantiator(jsonConfigInstantiator);
		}
	}
}
