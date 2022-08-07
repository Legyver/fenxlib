package com.legyver.fenxlib.api.alert;

import com.legyver.fenxlib.api.service.OrderedService;

/**
 * Factory to produce alerts
 * @param <T> the class of the alert
 */
public interface AlertFactory<T extends IAlert> extends OrderedService<AlertFactory> {
    /**
     * Make the alert widget.
     * @param alertTextContent the message for the alert body
     * @param level the (severity) level for the alert
     * @param timeout (optional) timeout for self-closing alerts
     * @return the alert
     */
    T makeAlert(AlertTextContent alertTextContent, Level level, Long timeout);
}
