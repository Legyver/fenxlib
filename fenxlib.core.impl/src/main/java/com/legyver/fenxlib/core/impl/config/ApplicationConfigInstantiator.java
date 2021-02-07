package com.legyver.fenxlib.core.impl.config;

import com.legyver.fenxlib.core.impl.files.MapDecoratorPojoInstantiator;

@FunctionalInterface
public interface ApplicationConfigInstantiator<T extends ApplicationConfig> extends MapDecoratorPojoInstantiator<T> {

}
