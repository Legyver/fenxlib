package com.legyver.fenxlib.config;

import com.legyver.fenxlib.files.MapDecoratorPojoInstantiator;

@FunctionalInterface
public interface ApplicationConfigInstantiator<T extends ApplicationConfig> extends MapDecoratorPojoInstantiator<T> {

}
