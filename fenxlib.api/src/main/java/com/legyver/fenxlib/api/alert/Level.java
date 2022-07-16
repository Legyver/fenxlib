package com.legyver.fenxlib.api.alert;

import com.legyver.fenxlib.api.icons.application.ApplicationIconAffiliated;

/**
 * Level for alert to display in the application
 */
public enum Level implements ApplicationIconAffiliated {
    /**
     * For informational messages
     */
    INFO,
    /**
     * For warning messages
     */
    WARNING,
    /**
     * For error messages
     */
    ERROR;

}
