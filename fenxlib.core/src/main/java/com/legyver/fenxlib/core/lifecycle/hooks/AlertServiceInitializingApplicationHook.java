package com.legyver.fenxlib.core.lifecycle.hooks;

import com.legyver.fenxlib.api.alert.AlertServiceRegistry;
import com.legyver.fenxlib.api.alert.IAlert;
import com.legyver.fenxlib.api.alert.Level;
import com.legyver.fenxlib.api.lifecycle.LifecyclePhase;
import com.legyver.fenxlib.api.lifecycle.hooks.ApplicationLifecycleHook;
import com.legyver.fenxlib.api.lifecycle.hooks.ExecutableHook;

import java.util.EnumMap;

/**
 * Hook to initialize AlertService
 */
public class AlertServiceInitializingApplicationHook implements ApplicationLifecycleHook {
    private final EnumMap<Level, IAlert.TargetRegion> alertLevelTargetRegions;

    /**
     * Construct a hook to set the alert level target regions on the alert service
     * @param alertLevelTargetRegions the map specifying the target region for alert level
     */
    public AlertServiceInitializingApplicationHook(EnumMap<Level, IAlert.TargetRegion> alertLevelTargetRegions) {
        this.alertLevelTargetRegions = alertLevelTargetRegions;
    }

    @Override
    public LifecyclePhase getLifecyclePhase() {
        return LifecyclePhase.INIT;
    }

    @Override
    public ExecutableHook getExecutableHook() {
        return () -> AlertServiceRegistry.getInstance().defaultLevelAlertLocations(alertLevelTargetRegions);
    }

    @Override
    public String getName() {
        return AlertServiceInitializingApplicationHook.class.getSimpleName();
    }
}
