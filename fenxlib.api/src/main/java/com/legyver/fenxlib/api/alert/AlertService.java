package com.legyver.fenxlib.api.alert;

import com.legyver.fenxlib.api.service.OrderedService;

import java.util.EnumMap;

/**
 * Service to handle the rendering of alerts in the application
 */
public interface AlertService extends OrderedService<AlertService> {
    /**
     * Display the alert
     * @param title the title of the alert
     * @param messages the message for the alert body
     * @param level the level of the alert
     * @param timeout the (optional) timeout for the alert.
     */
    void displayAlert(String title, String messages, Level level, Long timeout);

    /**
     * Set the default location for alerts
     * @param targetRegionEnumMap map containing default regions
     */
    void defaultLevelAlertLocations(EnumMap<Level, IAlert.TargetRegion> targetRegionEnumMap);
}
