package com.legyver.fenxlib.core.api.alert;

import javafx.scene.layout.StackPane;

/**
 * Service to handle the rendering of alerts in the application
 */
public interface AlertService {
    /**
     * Display the alert
     * @param stackPane The StackPane to display the alert over
     * @param title the title of the alert
     * @param messages the message for the alert body
     * @param level the level of the alert
     * @param timeout the (optional) timeout for the alert.
     */
    void displayAlert(StackPane stackPane, String title, String messages, Level level, Long timeout);

    /**
     * Set the factory to provide the alert widget
     * @param alertFactory the factory
     */
    void setFactory(AlertFactory<? extends IAlert> alertFactory);
}
