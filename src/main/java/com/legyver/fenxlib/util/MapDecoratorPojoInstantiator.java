package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.RawMapAware;
import java.util.Map;

@FunctionalInterface
public interface MapDecoratorPojoInstantiator<T extends RawMapAware> {
	T init(Map map);
}
