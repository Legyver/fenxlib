package com.legyver.fenxlib.api.config;

import com.legyver.fenxlib.api.files.util.MapDecoratorPojoInstantiator;

/**
 * Create a new instance of an application config if one does not exist.
 * @param <T> the type of the application config
 */
@FunctionalInterface
public interface ApplicationConfigInstantiator<T extends IApplicationConfig> extends MapDecoratorPojoInstantiator<T> {

}
