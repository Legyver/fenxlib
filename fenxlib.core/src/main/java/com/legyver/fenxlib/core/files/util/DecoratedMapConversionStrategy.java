package com.legyver.fenxlib.core.files.util;

import com.legyver.core.exception.CoreException;
import com.legyver.utils.jackiso.JacksonObjectMapper;
import com.legyver.utils.mapqua.mapbacked.RawMapAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Rather than converting a map to a POJO, decorate the Map with POJO-like getters/setters
 */
public class DecoratedMapConversionStrategy<U extends RawMapAware> implements FileConversionStrategy<U> {
	private final MapDecoratorPojoInstantiator<U> instantiator;

	/**
	 * Construct a conversion strategy that marshalls a POJO to String and vise-versa
	 * @param instantiator instantiates the POJO if there is no existing String to marshall
	 */
	public DecoratedMapConversionStrategy(MapDecoratorPojoInstantiator<U> instantiator) {
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
		return instantiator.init(map);
	}

	@Override
	public String fromModel(U model) throws CoreException {
		return JacksonObjectMapper.INSTANCE.writeValueAsStringWithPrettyPrint(model.getRawMap());
	}

}
