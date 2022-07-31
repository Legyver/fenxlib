package com.legyver.fenxlib.api.context;

/**
 * Scope for application resources such as CSS
 */
public enum ResourceScope {
    /**
     * Available to all the application and often used as a fallback when other scopes aren't specified
     */
    APPLICATION,
    /**
     * Available to all popups in the application
     */
    POPUPS,
    /**
     * Available to alerts only
     */
    ALERTS
}
