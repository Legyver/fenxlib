package com.legyver.fenxlib.config.json.util;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.files.util.FileConversionStrategy;
import com.legyver.fenxlib.api.files.util.MapDecoratorPojoInstantiator;
import com.legyver.fenxlib.config.json.JsonApplicationConfig;
import com.legyver.utils.jackiso.JacksonObjectMapper;
import com.legyver.utils.mapqua.mapbacked.RawMapAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Rather than converting a map to a POJO, decorate the Map with POJO-like getters/setters
 */
public class DecoratedMapConversionStrategy<U extends JsonApplicationConfig> implements FileConversionStrategy<U> {
	private final JsonConfigInstantiator<U> instantiator;

	/**
	 * Construct a conversion strategy that marshalls a POJO to String and vise-versa
	 * @param instantiator instantiates the POJO if there is no existing String to marshall
	 */
	public DecoratedMapConversionStrategy(JsonConfigInstantiator<U> instantiator) {
		this.instantiator = instantiator;
	}

	@Override
	public U toModel(String contents) throws CoreException {
		Map<String, Object> map = null;
		if (contents != null) {
			map = JacksonObjectMapper.INSTANCE.readValue(contents, Map.class);
		}
		if (map == null) {
			map = new HashMap<>();
		}
		return (U) instantiator.init(map);
	}

	@Override
	public String fromModel(U model) throws CoreException {
		return JacksonObjectMapper.INSTANCE.writeValueAsStringWithPrettyPrint(model.getRawMap());
	}

}
