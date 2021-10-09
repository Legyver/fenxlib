package com.legyver.fenxlib.widgets.snackbar.alert;

import com.legyver.fenxlib.core.api.alert.AlertFactory;
import com.legyver.fenxlib.core.api.alert.AlertService;
import com.legyver.fenxlib.core.api.alert.IAlert;
import com.legyver.fenxlib.core.api.alert.Level;
import javafx.scene.layout.StackPane;

/**
 * Default AlertService.  Displays alerts in a {@link com.legyver.fenxlib.widgets.snackbar.Snackbar} by default.
 */
public class AlertServiceImpl implements AlertService {

    private AlertFactory<? extends IAlert> alertFactory;

    /**
     * Construct an alert service that generates alert content using the {@link DefaultAlertFactory}
     */
    public AlertServiceImpl() {
        alertFactory = new DefaultAlertFactory();
    }

    @Override
    public void displayAlert(StackPane stackPane, String title, String messages, Level level, Long timeout) {
        IAlert alert = alertFactory.makeAlert(title, messages, level, timeout);
        alert.show(stackPane);
    }

    @Override
    public void setFactory(AlertFactory<? extends IAlert> alertFactory) {
        this.alertFactory = alertFactory;
    }
}
