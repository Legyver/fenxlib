package com.legyver.fenxlib.core.controls;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.service.NodeInstantiationServiceRegistry;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.css.Styleable;

import java.util.Map;

/**
 * Entry point to creating widgets based on factories on the classpath.
 */
public class ControlsFactory {
    /**
     * Make a component of the specified type based on the factory on the classpath
     * @param klass the class of the component
     * @param <T> the type of the component
     * @return the produced component
     * @throws CoreException if there is an error producing the component
     */
    public static <T extends Styleable> T make(Class<T> klass) throws CoreException {
        return make(klass, null, null);
    }

    /**
     * Make a component of the specified type based on the factory on the classpath
     * @param klass the class of the component
     * @param options any factory-supported options to be used to create the component
     * @param <T> the type of the component
     * @return the produced component
     * @throws CoreException if there is an error producing the component
     */
    public static <T extends Styleable> T make(Class<T> klass, Map options) throws CoreException {
        return make(klass, null, options);
    }

    /**
     * Make a component of the specified type based on the factory on the classpath
     * @param klass the class of the component
     * @param locationContext the location of the component in your application
     * @param <T> the type of the component
     * @return the produced component
     * @throws CoreException if there is an error producing the component
     */
    public static <T extends Styleable> T make(Class<T> klass, LocationContext locationContext) throws CoreException {
        return make(klass, locationContext, null);
    }

    /**
     * Make a component of the specified type based on the factory on the classpath
     * @param klass the class of the component
     * @param locationContext the location of the component in your application
     * @param options any factory-supported options to be used to create the component
     * @param <T> the type of the component
     * @return the produced component
     * @throws CoreException if there is an error producing the component
     */
    public static <T extends Styleable> T make(Class<T> klass, LocationContext locationContext, Map options) throws CoreException {
        return NodeInstantiationServiceRegistry.getInstance().instantiate(klass, locationContext, options);
    }
}
