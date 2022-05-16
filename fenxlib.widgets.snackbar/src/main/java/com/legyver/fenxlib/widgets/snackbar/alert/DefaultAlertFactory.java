package com.legyver.fenxlib.widgets.snackbar.alert;

import com.legyver.fenxlib.api.alert.AlertFactory;
import com.legyver.fenxlib.api.alert.IAlert;
import com.legyver.fenxlib.api.alert.Level;
import com.legyver.fenxlib.widgets.snackbar.Snackbar;

/**
 * Default alert factory.  This creates an alert based on the {@link Snackbar}
 */
public class DefaultAlertFactory implements AlertFactory<IAlert> {

    @Override
    public IAlert makeAlert(String title, String messages, Level level, Long timeout) {
        Snackbar snackbar = new Snackbar() {
            @Override
            public long getTimeoutInMillis() {
                return timeout != null ? timeout : super.getTimeoutInMillis();
            }
        };
        snackbar.setMessage(messages);
        snackbar.setLevel(level);

        return snackbar;
    }

    @Override
    public int order() {
        return 0;
    }
}
