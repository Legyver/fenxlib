package com.legyver.fenxlib.widgets.snackbar.alert;

import com.legyver.fenxlib.api.alert.AlertFactory;
import com.legyver.fenxlib.api.alert.IAlert;
import com.legyver.fenxlib.api.service.OrderedServiceDelegator;

import java.util.ServiceLoader;

/**
 * Registry of factories to create alerts
 */
public class AlertFactoryRegistry {
    private final OrderedServiceDelegator<AlertFactory> orderedServiceDelegator;
    private static AlertFactoryRegistry instance;

    private AlertFactoryRegistry() {
        ServiceLoader<AlertFactory> alertFactoryServiceLoader = ServiceLoader.load(AlertFactory.class);
        orderedServiceDelegator = new OrderedServiceDelegator<>(alertFactoryServiceLoader);
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
        return orderedServiceDelegator.getDelegate();
    }
}
