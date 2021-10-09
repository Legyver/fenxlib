package com.legyver.fenxlib.widgets.snackbar.alert;

import com.jfoenix.controls.JFXDialog;
import com.legyver.fenxlib.core.api.alert.IAlert;
import com.legyver.fenxlib.widgets.snackbar.Snackbar;

/**
 * Tie in the default JFXDialog with the alert service
 */
public class SnackbarAlert extends JFXDialog implements IAlert {
    /**
     * Construct a dialog to display an alert in.
     * @param snackbar the widget displaying the alert data
     * @param transition the transition
     */
    public SnackbarAlert(Snackbar snackbar, DialogTransition transition) {
        super(null, snackbar, transition);
    }

}
