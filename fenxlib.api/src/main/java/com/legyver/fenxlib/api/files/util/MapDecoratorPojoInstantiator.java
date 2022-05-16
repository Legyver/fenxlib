package com.legyver.fenxlib.api.files.util;

import com.legyver.utils.mapqua.mapbacked.RawMapAware;
import java.util.Map;

/**
 * Creates a POJO with the values defined in a map
 * @param <T> the type of the POJO
 */
@FunctionalInterface
public interface MapDecoratorPojoInstantiator<T extends RawMapAware> {
	/**
	 * Initialize a POJO from a Map of values
	 * @param map the values to set on the POJO
	 * @return the POJO
	 */
	T init(Map map);
}
