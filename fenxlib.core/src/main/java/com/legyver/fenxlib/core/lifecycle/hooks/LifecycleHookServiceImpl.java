package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.fenxlib.api.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.api.config.load.ApplicationConfigProvider;
import com.legyver.fenxlib.api.config.load.ApplicationHome;
import com.legyver.fenxlib.api.config.options.ApplicationOptions;
import com.legyver.fenxlib.api.context.ApplicationContext;
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
        ApplicationConfigProvider applicationConfigProvider = applicationOptions.getApplicationConfigProvider();

        lifecycleHookMap.computeIfAbsent(InitLoggingApplicationLifecycleHook.class, (name) -> {
            InitLoggingApplicationLifecycleHook result = null;
            if (applicationOptions.isUsesLogging() && !lifecycleHookMap.containsHook(InitLoggingApplicationLifecycleHook.class.getName())) {
                String appName = applicationOptions.getApplicationName();
                if (applicationConfigProvider instanceof ApplicationHome) {
                    result = new InitLoggingApplicationLifecycleHook((ApplicationHome) applicationConfigProvider, appName);
                } else {
                    result = new InitLoggingApplicationLifecycleHook(appName);
                }
            }
            return result;
        });

        ApplicationConfigInstantiator appConfigInstantiator = applicationOptions.getAppConfigInstantiator();
        lifecycleHookMap.computeIfAbsent(LoadConfigApplicationLifecycleHook.class, name -> {
            return new LoadConfigApplicationLifecycleHook(appConfigInstantiator, applicationConfigProvider);
        });
        //save config Mixin
        if (applicationOptions.isUsesAutoSaveConfig()) {
            lifecycleHookMap.computeIfAbsent(PreShutdownConfigSyncLifecycleHook.class, name -> new PreShutdownConfigSyncLifecycleHook());
            lifecycleHookMap.computeIfAbsent(AutoSaveConfigApplicationLifecycleHook.class, name -> new AutoSaveConfigApplicationLifecycleHook(applicationConfigProvider));
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
