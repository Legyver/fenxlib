package com.legyver.fenxlib.core.impl.config;

import com.legyver.fenxlib.core.impl.files.MapDecoratorPojoInstantiator;

/**
 * Create a new instance of an application config if one does not exist.
 * @param <T> the type of the application config
 */
@FunctionalInterface
public interface ApplicationConfigInstantiator<T extends ApplicationConfig> extends MapDecoratorPojoInstantiator<T> {

}
