package com.legyver.fenxlib.core.impl.alert;

import com.legyver.fenxlib.core.api.alert.AlertFactory;
import com.legyver.fenxlib.core.api.alert.AlertService;
import com.legyver.fenxlib.core.api.alert.IAlert;
import com.legyver.fenxlib.core.api.alert.Level;
import javafx.scene.layout.StackPane;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Registry for {@link AlertService}
 */
public class AlertServiceRegistry {
    private final ServiceLoader<AlertService> alertServiceServiceLoader;
    private static AlertServiceRegistry instance;

    private AlertServiceRegistry() {
        alertServiceServiceLoader = ServiceLoader.load(AlertService.class);
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
     * @param stackPane the StackPane to display the alert over
     * @param title the title of the alert
     * @param message the message detail of the alert
     * @param level the level INFO/WARNING/ERROR of the alert
     * @param timeout an optional parameter that (depending on the alert service) can auto-hide the alert after a period.
     */
    public void displayAlert(StackPane stackPane, String title, String message, Level level, Long timeout) {
        for (Iterator<AlertService> it = alertServiceServiceLoader.iterator(); it.hasNext();) {
            AlertService alertService = it.next();
            alertService.displayAlert(stackPane, title, message, level, timeout);
        }
    }

    /**
     * Set the alert factory to be used by all {@link AlertService} when generating alerts
     * @param alertFactory the alert factory to use.
     */
    public void setFactory(AlertFactory<? extends IAlert> alertFactory) {
        if (alertFactory != null) {
            for (Iterator<AlertService> it = alertServiceServiceLoader.iterator(); it.hasNext();) {
                AlertService alertService = it.next();
                alertService.setFactory(alertFactory);
            }
        }
    }
}
