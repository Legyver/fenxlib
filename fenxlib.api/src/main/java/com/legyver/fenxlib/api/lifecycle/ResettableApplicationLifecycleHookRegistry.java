package com.legyver.fenxlib.api.lifecycle;

/**
 * Application lifecycle hook registry that supports a method to reset the application lifecycle
 */
public interface ResettableApplicationLifecycleHookRegistry extends IApplicationLifecycleHookRegistry {
    /**
     * Clear all lifecycle hooks and reset the application state machine
     */
    void reset();
}
