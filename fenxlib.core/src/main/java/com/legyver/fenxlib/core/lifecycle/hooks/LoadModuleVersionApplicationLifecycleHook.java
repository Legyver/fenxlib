package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;
import com.legyver.fenxlib.api.version.ModuleVersionServiceRegistry;

/**
 * Load versions of the classpath modules
 */
public class LoadModuleVersionApplicationLifecycleHook implements ApplicationLifecycleHook {
    @Override
    public LifecyclePhase getLifecyclePhase() {
        return LifecyclePhase.PRE_INIT;
    }

    @Override
    public ExecutableHook getExecutableHook() {
        return () -> ModuleVersionServiceRegistry.getInstance().loadVersions();
    }

    @Override
    public String getName() {
        return LoadModuleVersionApplicationLifecycleHook.class.getSimpleName();
    }
}
