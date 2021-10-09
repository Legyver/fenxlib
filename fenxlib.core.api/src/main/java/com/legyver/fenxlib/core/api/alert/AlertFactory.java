package com.legyver.fenxlib.core.api.alert;

/**
 * Factory to produce alerts
 * @param <T> the class of the alert
 */
public interface AlertFactory<T extends IAlert> {
    /**
     * Make the alert widget.
     * @param title the title of the alert
     * @param messages the message for the alert body
     * @param level the (severity) level for the alert
     * @param timeout (optional) timeout for self-closing alerts
     * @return the alert
     */
    T makeAlert(String title, String messages, Level level, Long timeout);
}
