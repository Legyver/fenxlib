package com.legyver.fenxlib.widgets.snackbar.alert;

import com.legyver.fenxlib.api.alert.AlertFactory;
import com.legyver.fenxlib.api.alert.IAlert;
import com.legyver.fenxlib.api.alert.Level;
import com.legyver.fenxlib.widgets.snackbar.CloseableSnackbar;
import com.legyver.fenxlib.widgets.snackbar.Snackbar;

/**
 * Default alert factory.  This creates an alert based on the {@link Snackbar}
 */
public class DefaultAlertFactory implements AlertFactory<IAlert> {

    @Override
    public IAlert makeAlert(String title, String messages, Level level, Long timeout) {
        Snackbar snackbar;
        if (decorateWithCloseHeader(level, timeout)) {
            snackbar = new CloseableSnackbar(timeout);
            snackbar.setPrefHeight(60);
        } else {
            snackbar = new Snackbar(timeout);
            snackbar.setPrefHeight(30);
        }
        snackbar.setMessage(messages);
        snackbar.setLevel(level);
        snackbar.setPrefWidth(150);

        snackbar.setMinSize(150, 25);

        return snackbar;
    }

    /**
     * If this method returns true, then the snackbar will have a close button in the header.
     * The default implementation adds a close button if
     * - the timeout is not specified (null or a negative number)
     * - the timeout is bigger than 1 second
     * @param level the level of the alert
     * @param timeout the timeout for the alert
     * @return true if a close button should be added to the alert
     */
    protected boolean decorateWithCloseHeader(Level level, Long timeout) {
        //no point decorating it with a close button for less than 1 second
        return (timeout == null || timeout > 1000L || timeout < 0);
    }

    @Override
    public int order() {
        return 0;
    }
}
