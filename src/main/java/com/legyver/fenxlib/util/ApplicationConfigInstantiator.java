package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.ApplicationConfig;

@FunctionalInterface
public interface ApplicationConfigInstantiator<T extends ApplicationConfig> extends MapDecoratorPojoInstantiator<T> {

}
