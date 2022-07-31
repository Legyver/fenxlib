package com.legyver.fenxlib.api.alert;

import javafx.beans.property.BooleanProperty;

/**
 * An alert that can be manually dismissed
 */
public interface ICloseableAlert extends IAlert {
    /**
     * Property notified if the user wants to dismiss the alert
     * @return the property
     */
    BooleanProperty closeProperty();
}
