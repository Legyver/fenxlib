package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.options.ControlOptions;

/**
 * Factory responsible for making a control
 * @param <T> the type of the control
 * @param <U> the type of the options defining the control
 */
public interface ControlFactory<T, U extends ControlOptions<T>> {
    /**
     * Create a node
     * @param locationContext the location context to register the widget as
     * @param options options to use to construct the widget.  Do not pass null in here, rather call {@link #makeNode(LocationContext)}
     * @return the constructed widget
     * @throws CoreException if there is an error
     */
    T makeNode(LocationContext locationContext, U options) throws CoreException;

    /**
     * Create a node
     * @param locationContext the location context to register the widget as
     * @return the constructed widget
     * @throws CoreException if there is an error
     */
    default T makeNode(LocationContext locationContext) throws CoreException {
        return makeNode(locationContext, newOptions());
    }

    /**
     * Instantiate new Options of the appropriate class as a safeguard against NPEs
     * @return new options
     * @throws CoreException if there is a problem creating the control
     */
    U newOptions() throws CoreException;

    /**
     * Most factories don't have their own built-in handling for instantiating a location context.
     * By default, this method returns true.  Override if yours does.
     * @return true if the factory does not support instantiating a standalone location context.
     */
    default boolean requiresLocationContext() {
        return true;
    }
}
