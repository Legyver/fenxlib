package com.legyver.fenxlib.widgets.snackbar.alert;

import com.jfoenix.controls.JFXDialog;
import com.legyver.fenxlib.core.api.alert.AlertFactory;
import com.legyver.fenxlib.core.api.alert.Level;
import com.legyver.fenxlib.core.api.util.DelayedAction;
import com.legyver.fenxlib.widgets.snackbar.Snackbar;
import javafx.application.Platform;

import java.time.LocalDateTime;

/**
 * Default alert factory.  This creates an alert based on the {@link Snackbar}
 * If level is INFO or WARN and a timeout is supplied, then the alert will automatically close after specified period.
 * Otherwise, the alert will have a button to close the alert
 */
public class DefaultAlertFactory implements AlertFactory<SnackbarAlert> {

    @Override
    public SnackbarAlert makeAlert(String title, String messages, Level level, Long timeout) {
        Snackbar snackbar = new Snackbar();
        snackbar.setMessage(messages);
        snackbar.setLevel(level);

        SnackbarAlert dialog = new SnackbarAlert(snackbar, JFXDialog.DialogTransition.RIGHT);

        if ((level == Level.INFO || level == Level.WARNING) && timeout != null && timeout > 0) {
            Platform.runLater(() -> new DelayedAction(() -> dialog.close(), timeout).run());
        }
        return dialog;
    }
}
