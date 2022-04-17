package com.legyver.fenxlib.widgets.snackbar.alert;

import com.legyver.fenxlib.core.alert.AlertFactory;
import com.legyver.fenxlib.core.alert.IAlert;
import com.legyver.fenxlib.core.service.OrderedServiceDelagator;

import java.util.ServiceLoader;

/**
 * Registry of factories to create alerts
 */
public class AlertFactoryRegistry {
    private final OrderedServiceDelagator<AlertFactory> orderedServiceDelagator;
    private static AlertFactoryRegistry instance;

    private AlertFactoryRegistry() {
        ServiceLoader<AlertFactory> alertFactoryServiceLoader = ServiceLoader.load(AlertFactory.class);
        orderedServiceDelagator = new OrderedServiceDelagator<>(alertFactoryServiceLoader);
    }

    /**
     * Get the singleton instance of the registry
     * @return this registry
     */
    public static AlertFactoryRegistry getInstance() {
        if (instance == null) {
            synchronized (AlertFactoryRegistry.class) {
                if (instance == null) {
                    instance = new AlertFactoryRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Get the highest alert factory
     * @return the alert factory
     */
    @SuppressWarnings("unchecked")
    public AlertFactory<? extends IAlert> getFactoryFromClasspath() {
        return orderedServiceDelagator.getDelegate();
    }
}
