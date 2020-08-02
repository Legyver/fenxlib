package com.legyver.fenxlib.core.config;

import com.legyver.fenxlib.core.files.MapDecoratorPojoInstantiator;

@FunctionalInterface
public interface ApplicationConfigInstantiator<T extends ApplicationConfig> extends MapDecoratorPojoInstantiator<T> {

}
