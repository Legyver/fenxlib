package com.legyver.fenxlib.api.alert;

import javafx.collections.ObservableList;

/**
 * Alert pane that will display application alerts over either the top-right, top-left, bottom-right or bottom-left of an application
 */
public interface IAlertPane {
    /**
     * Get alerts to be displayed in the bottom right of the main application
     * @return bottom right alerts
     */
    ObservableList<IAlert> getBottomRightAlerts();
    /**
     * Get alerts to be displayed in the bottom left of the main application
     * @return bottom left alerts
     */
    ObservableList<IAlert> getBottomLeftAlerts();
    /**
     * Get alerts to be displayed in the top right of the main application
     * @return top right alerts
     */
    ObservableList<IAlert> getTopRightAlerts();
    /**
     * Get alerts to be displayed in the top left of the main application
     * @return top left alerts
     */
    ObservableList<IAlert> getTopLeftAlerts();
}
