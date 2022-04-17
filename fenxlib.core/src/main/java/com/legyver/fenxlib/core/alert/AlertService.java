package com.legyver.fenxlib.core.alert;

import com.legyver.fenxlib.core.service.OrderedService;

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
     * Set the factory to provide the alert widget
     * @param alertFactory the factory
     */
    void setFactory(AlertFactory<? extends IAlert> alertFactory);
}
