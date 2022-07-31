package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.fenxlib.api.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.api.config.load.ApplicationHome;
import com.legyver.fenxlib.api.config.options.ApplicationOptions;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.lifecycle.hooks.AlertServiceInitializingApplicationHook;
import com.legyver.fenxlib.api.lifecycle.hooks.LifecycleHookMap;
import com.legyver.fenxlib.api.lifecycle.hooks.LifecycleHookService;
import com.legyver.fenxlib.core.lifecycle.ApplicationLifecycleHookRegistry;

/**
 * Default lifecycle hook service.
 * Provides lifecycle hooks based on configured application options
 */
public class LifecycleHookServiceImpl implements LifecycleHookService {

    /**
     * Add default hooks
     * @param applicationOptions application options specifying what options are supported
     * @param lifecycleHookMap the map of hooks to add them to
     */
    protected void addDefaultHooks(ApplicationOptions applicationOptions, LifecycleHookMap lifecycleHookMap) {
        String appName = applicationOptions.getApplicationName();
        String applicationConfigName = applicationOptions.getApplicationConfigName();

        lifecycleHookMap.computeIfAbsent(InitLoggingApplicationLifecycleHook.class, name -> {
            InitLoggingApplicationLifecycleHook result = null;
            if (applicationOptions.isUsesLogging()) {
                result = new InitLoggingApplicationLifecycleHook(appName);
            }
            return result;
        });
        lifecycleHookMap.computeIfAbsent(AlertServiceInitializingApplicationHook.class, name -> {
            return new AlertServiceInitializingApplicationHook(applicationOptions.getAlertLevelTargetRegions());
        });

        ApplicationConfigInstantiator appConfigInstantiator = applicationOptions.getAppConfigInstantiator();
        lifecycleHookMap.computeIfAbsent(LoadConfigApplicationLifecycleHook.class, name -> {
            return new LoadConfigApplicationLifecycleHook(appConfigInstantiator, appName, applicationConfigName);
        });
        //save config Mixin
        if (applicationOptions.isUsesAutoSaveConfig()) {
            lifecycleHookMap.computeIfAbsent(PreShutdownConfigSyncLifecycleHook.class, name -> new PreShutdownConfigSyncLifecycleHook());
            lifecycleHookMap.computeIfAbsent(AutoSaveConfigApplicationLifecycleHook.class, name -> new AutoSaveConfigApplicationLifecycleHook(applicationConfigName));
        }
        lifecycleHookMap.computeIfAbsent(InitComponentRegistryLifecycleHook.class, name -> new InitComponentRegistryLifecycleHook());
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public boolean initsApplicationLifecycleRegistry() {
        return true;
    }

    @Override
    public void initApplicationLifecycleRegistry() {
        ApplicationContext.setApplicationLifecycleHookRegistry(new ApplicationLifecycleHookRegistry());
    }

    @Override
    public void augmentLifecycleHooks(ApplicationOptions applicationOptions, LifecycleHookMap lifecycleHookMap) {
        addDefaultHooks(applicationOptions, lifecycleHookMap);
    }
}
