package com.legyver.fenxlib.core.impl.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.legyver.utils.mapqua.mapbacked.RawMapAware;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Rather than converting a map to a POJO, decorate the Map with POJO-like getters/setters
 */
public class DecoratedMapConversionStrategy<U extends RawMapAware> implements FileConversionStrategy<U> {
	private final MapDecoratorPojoInstantiator<U> instantiator;
	
	public DecoratedMapConversionStrategy(MapDecoratorPojoInstantiator<U> instantiator) {
		this.instantiator = instantiator;
	}
			
	@Override
	public U toModel(String contents) throws IOException, IllegalAccessException {
		Type type = new TypeToken<Map>() {
		}.getType();
		Map<String, Map> map = new Gson().fromJson(contents, type);
		if (map == null) {
			map = new HashMap<>();
		}
		return instantiator.init(map);
	}

	@Override
	public String fromModel(U model) throws IOException, IllegalAccessException {
		return new GsonBuilder().setPrettyPrinting().create().toJson(model.getRawMap());
	}

}
