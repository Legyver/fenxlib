package com.legyver.fenxlib.core.lifecycle.hooks;


import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.api.i18n.ResourceBundleServiceRegistry;

import java.util.List;

/**
 * Resource hook to load i18n resources
 */
public class I18NResourceHook implements ApplicationLifecycleHook {
    private final List<String> bundleBaseNames;

    /**
     * Construct a hook to load the specified resource bundles
     * @param bundleBaseNames the bundles to load
     */
    public I18NResourceHook(List<String> bundleBaseNames) {
        this.bundleBaseNames = bundleBaseNames;
    }

    @Override
    public LifecyclePhase getLifecyclePhase() {
        return LifecyclePhase.INIT;
    }

    @Override
    public ExecutableHook getExecutableHook() {
        return () -> {
            ResourceBundleServiceRegistry.getInstance().setBundleBaseNames(bundleBaseNames);
        };
    }

    @Override
    public String getName() {
        return I18NResourceHook.class.getSimpleName();
    }
}
