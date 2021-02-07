package com.legyver.fenxlib.core.impl.files;

import com.legyver.utils.mapqua.mapbacked.RawMapAware;
import java.util.Map;

/**
 * Creates a POJO with the values defined in a map
 * @param <T>
 */
@FunctionalInterface
public interface MapDecoratorPojoInstantiator<T extends RawMapAware> {
	T init(Map map);
}
