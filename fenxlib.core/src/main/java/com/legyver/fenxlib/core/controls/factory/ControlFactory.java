package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.options.ControlOptions;

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
     */
    U newOptions();
}
