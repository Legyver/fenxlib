package com.legyver.fenxlib.core.config;

import com.legyver.fenxlib.core.files.util.MapDecoratorPojoInstantiator;

/**
 * Create a new instance of an application config if one does not exist.
 * @param <T> the type of the application config
 */
@FunctionalInterface
public interface ApplicationConfigInstantiator<T extends ApplicationConfig> extends MapDecoratorPojoInstantiator<T> {

}
