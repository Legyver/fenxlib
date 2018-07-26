package com.legyver.fenxlib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.legyver.fenxlib.config.RawMapAware;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Rather than converting a map to a POJO, decorate the Map with POJO-like getters/setters
 */
public class DecoratedMapConversionStrategy<U extends RawMapAware> implements FileConversionStrategy<FileContext, U> {
	private final MapDecoratorPojoInstantiator<U> instantiator;
	
	public DecoratedMapConversionStrategy(MapDecoratorPojoInstantiator<U> instantiator) {
		this.instantiator = instantiator;
	}
			
	@Override
	public U toModel(String contents, FileContext context) throws IOException, IllegalAccessException {
		Type type = new TypeToken<Map>() {
		}.getType();
		LinkedTreeMap<String, Map> map = new Gson().fromJson(contents, type);
		if (map == null) {
			map = new LinkedTreeMap<>();
		}
		return instantiator.init(map);
	}

	@Override
	public String fromModel(U model, FileContext context) throws IOException, IllegalAccessException {
		return new GsonBuilder().setPrettyPrinting().create().toJson(model.getRawMap());
	}

}
