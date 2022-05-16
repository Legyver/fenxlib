package com.legyver.fenxlib.api.lifecycle.hooks;

import com.legyver.fenxlib.api.config.options.ApplicationOptions;
import com.legyver.fenxlib.api.service.OrderedService;

import java.util.List;
import java.util.Map;

/**
 * Service for registering lifecycle hooks
 */
public interface LifecycleHookService extends OrderedService<LifecycleHookService> {

    /**
     * Return true if this service should be used to init the application lifecycle registry
     * @return true if this service implementation should provide the {@link #initApplicationLifecycleRegistry()} implementation
     */
    default boolean initsApplicationLifecycleRegistry() {
        return false;
    }

    /**
     * Initialize the lifecycle registry
     */
    default void initApplicationLifecycleRegistry() {
        //noop
    }

    /**
     * Add any additional lifecycle hooks to the map.
     * @param applicationOptions the application options
     * @param lifecycleHookMap a map where the key is the hook.name and the value is the list of hooks with that name
     */
    void augmentLifecycleHooks(ApplicationOptions applicationOptions, LifecycleHookMap lifecycleHookMap);
}
