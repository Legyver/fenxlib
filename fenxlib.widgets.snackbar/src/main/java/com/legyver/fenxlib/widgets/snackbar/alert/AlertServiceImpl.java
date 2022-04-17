package com.legyver.fenxlib.widgets.snackbar.alert;

import com.legyver.fenxlib.core.alert.AlertFactory;
import com.legyver.fenxlib.core.alert.AlertService;
import com.legyver.fenxlib.core.alert.IAlert;
import com.legyver.fenxlib.core.alert.Level;
import com.legyver.fenxlib.core.controls.factory.SceneFactory;
import com.legyver.fenxlib.core.controls.popup.AlertPane;
import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.util.DelayedAction;
import javafx.application.Platform;
import javafx.collections.ObservableList;

/**
 * Default AlertService.  Displays alerts in a {@link com.legyver.fenxlib.widgets.snackbar.Snackbar} by default.
 */
public class AlertServiceImpl implements AlertService {
    private AlertFactory<? extends IAlert> alertFactory;

    /**
     * Construct an alert service that generates alert content using the {@link DefaultAlertFactory}
     */
    public AlertServiceImpl() {
        alertFactory = AlertFactoryRegistry.getInstance().getFactoryFromClasspath();
    }

    @Override
    public void displayAlert(String title, String messages, Level level, Long timeout) {
        IAlert alert = alertFactory.makeAlert(title, messages, level, timeout);

        AlertPane alertPane = (AlertPane) new ComponentQuery.QueryBuilder()
                .inRegion(SceneFactory.FENXLIB_MAIN_APPLICATION_PANE)
                .named(SceneFactory.FENXLIB_ALERT_PANE).execute()
                .get();
        ObservableList<IAlert> alerts = alert.getTargetRegion().getRegionAlerts(alertPane);
        alerts.add(alert);

        if (alert.getTimeoutInMillis() < Long.MAX_VALUE) {
            Runnable removeAlert = () -> alerts.remove(alert);
            Platform.runLater(new DelayedAction(removeAlert, alert.getTimeoutInMillis()));
        }
    }

    @Override
    public void setFactory(AlertFactory<? extends IAlert> alertFactory) {
        this.alertFactory = alertFactory;
    }

    @Override
    public int order() {
        return 0;
    }
}
