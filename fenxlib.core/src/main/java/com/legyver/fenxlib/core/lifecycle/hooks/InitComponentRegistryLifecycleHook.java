package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.core.locator.query.DefaultComponentRegistry;
import com.legyver.fenxlib.api.locator.query.QueryableComponentRegistry;

/**
 * Lifecycle hook to initialize the component registry during the POST_INIT phase
 */
public class InitComponentRegistryLifecycleHook implements ApplicationLifecycleHook {
    @Override
    public LifecyclePhase getLifecyclePhase() {
        return LifecyclePhase.POST_INIT;
    }

    @Override
    public ExecutableHook getExecutableHook() {
        return () -> {
            QueryableComponentRegistry componentRegistry = ApplicationContext.getComponentRegistry();
            if (componentRegistry == null) {
                ApplicationContext.setComponentRegistry(new DefaultComponentRegistry());
            }
        };
    }

    @Override
    public String getName() {
        return InitComponentRegistryLifecycleHook.class.getSimpleName();
    }
}
