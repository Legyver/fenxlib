package com.legyver.fenxlib.api.lifecycle.hooks;

import com.legyver.fenxlib.api.config.options.ApplicationOptions;
import com.legyver.fenxlib.api.service.OrderedServiceDelegator;

import java.util.*;
import java.util.function.Predicate;

/**
 * Registry for all Lifecycle Hook Services.
 * Lifecycle hook services are responsible for registering all lifecycle hooks
 */
public class LifecycleHookServiceRegistry {
        private final OrderedServiceDelegator<LifecycleHookService> orderedServiceDelegator;
        private static LifecycleHookServiceRegistry instance;

        private LifecycleHookServiceRegistry() {
            ServiceLoader<LifecycleHookService> serviceLoader = ServiceLoader.load(LifecycleHookService.class);
            orderedServiceDelegator = new OrderedServiceDelegator<>(serviceLoader, new Predicate<>() {
                @Override
                public boolean test(LifecycleHookService lifecycleHookService) {
                    return lifecycleHookService.initsApplicationLifecycleRegistry();
                }
            });
        }

        /**
         * Retrieve a singleton instance of the registry
         * @return the instance
         */
        public static LifecycleHookServiceRegistry getInstance() {
            if (instance == null) {
                synchronized (LifecycleHookServiceRegistry.class) {
                    if (instance == null) {
                        instance = new LifecycleHookServiceRegistry();
                    }
                }
            }
            return instance;
        }

    /**
     * Load application lifecycle hooks.
     * The highest-preference (lowest-ordered) LifecycleHookService that returns true for
     * {@link LifecycleHookService#initsApplicationLifecycleRegistry()} will be used to initialize
     * the application lifecycle hook registry via {@link LifecycleHookService#initApplicationLifecycleRegistry()}
     * @param applicationOptions the application options to use when initing lifecycle hooks
     */
    public void loadLifecycleHooks(ApplicationOptions applicationOptions) {
        orderedServiceDelegator.getDelegate().initApplicationLifecycleRegistry();
        LifecycleHookMap lifecycleHookMap = getHooksAsMap(applicationOptions);
        for (Iterator<LifecycleHookService> it = orderedServiceDelegator.iterator(); it.hasNext();) {
            LifecycleHookService lifecycleHookService = it.next();
            lifecycleHookService.augmentLifecycleHooks(applicationOptions, lifecycleHookMap);
        }
        lifecycleHookMap.registerHooks();
    }

    private LifecycleHookMap getHooksAsMap(ApplicationOptions applicationOptions) {
        LifecycleHookMap lifecycleHookMap = new LifecycleHookMap();
        for (ApplicationLifecycleHook hook : applicationOptions.getHooksToRegister()) {
            lifecycleHookMap.addLifecycleHook(hook.getName(), hook);
        }
        return lifecycleHookMap;
    }


}
