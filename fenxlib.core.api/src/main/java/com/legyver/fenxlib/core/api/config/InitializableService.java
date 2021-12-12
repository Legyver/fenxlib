package com.legyver.fenxlib.core.api.config;

/**
 * A service that accepts an initializer
 * @param <T> the type of the initializer
 */
public interface InitializableService<T> {
    /**
     * Initialize the service
     * @param initializer the initializer
     */
    void init(T initializer);
}
