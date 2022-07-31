package com.legyver.fenxlib.api.controls.service;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.options.ControlOptions;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.service.OrderedService;
import javafx.css.Styleable;

/**
 * Service to provide the default instantiation of JavaFX Controls based on type.
 */
public interface NodeInstantiationService extends OrderedService<NodeInstantiationService> {

    /**
     * Instantiate a JavaFX control
     * @param <T> the type of the JavaFX control
     * @param klass the control you want to instantiate
     * @param locationContext the location to register the control in
     * @param options any options to provide the constructor during instantiation
     * @return the new control
     * @throws CoreException if there is an error constructing the control
     */
    <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, ControlOptions<T> options) throws CoreException;

    /**
     * Get the preference for the instantiation service.  The default pure JavaFX instantiation service preference is 0.
     * Choose a number than less if you want to usurp the default service, and greater if you want to just use your factory as a fallback for unknown types.
     * @return the preference to weight this service for creating JavaFX controls
     */
    int getPreference();

    default int order() {
        return getPreference();
    }
}
