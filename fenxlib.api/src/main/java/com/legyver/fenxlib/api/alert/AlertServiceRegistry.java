package com.legyver.fenxlib.api.alert;

import com.legyver.fenxlib.api.service.OrderedServiceDelegator;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Registry for {@link AlertService}
 */
public class AlertServiceRegistry {
    private final OrderedServiceDelegator<AlertService> orderedServiceDelegator;
    private static AlertServiceRegistry instance;

    private AlertServiceRegistry() {
        ServiceLoader<AlertService> alertServiceServiceLoader = ServiceLoader.load(AlertService.class);
        orderedServiceDelegator = new OrderedServiceDelegator<>(alertServiceServiceLoader);
    }

    /**
     * Retrieve a singleton instance of the registry
     * @return the instance
     */
    public static AlertServiceRegistry getInstance() {
        if (instance == null) {
            synchronized (AlertServiceRegistry.class) {
                if (instance == null) {
                    instance = new AlertServiceRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Display an alert by delegating to any alert services on the classpath.
     * If no alert services are present, no alert is dispatched.
     *
     * @param title the title of the alert
     * @param message the message detail of the alert
     * @param level the level INFO/WARNING/ERROR of the alert
     * @param timeout an optional parameter that (depending on the alert service) can auto-hide the alert after a period.
     */
    public void displayAlert(String title, String message, Level level, Long timeout) {
        for (Iterator<AlertService> it = orderedServiceDelegator.iterator(); it.hasNext();) {
            AlertService alertService = it.next();
            alertService.displayAlert(title, message, level, timeout);
        }
    }

    /**
     * Set the alert factory to be used by all {@link AlertService} when generating alerts
     * @param alertFactory the alert factory to use.
     */
    public void setFactory(AlertFactory<? extends IAlert> alertFactory) {
        if (alertFactory != null) {
            for (Iterator<AlertService> it = orderedServiceDelegator.iterator(); it.hasNext();) {
                AlertService alertService = it.next();
                alertService.setFactory(alertFactory);
            }
        }
    }
}
