package com.legyver.fenxlib.core.api.alert;

import javafx.scene.layout.StackPane;

/**
 * Tagging interface for an alert
 */
public interface IAlert {
    /**
     * Display the alert.
     * @param stackPane the StackPane to display the alert over
     */
    void show(StackPane stackPane);
}
