package com.legyver.fenxlib.files;

import com.legyver.util.mapqua.mapbacked.RawMapAware;
import java.util.Map;

@FunctionalInterface
public interface MapDecoratorPojoInstantiator<T extends RawMapAware> {
	T init(Map map);
}
