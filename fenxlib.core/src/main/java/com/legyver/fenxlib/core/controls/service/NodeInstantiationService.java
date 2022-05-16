package com.legyver.fenxlib.core.controls.service;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.service.OrderedService;
import javafx.css.Styleable;

import java.util.Map;

/**
 * Service to provide the default instantiation of JavaFX Controls based on type.
 */
public interface NodeInstantiationService extends OrderedService<NodeInstantiationService> {

    /**
     * Instantiate a JavaFX control
     * @param klass the control you want to instantiate
     * @param locationContext the location to register the control in
     * @param options any options to provide the constructor during instantiation
     * @param <T> the type of the JavaFX control
     * @return the new control
     * @throws CoreException if there is an error constructing the control
     */
    <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, Map options) throws CoreException;

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
