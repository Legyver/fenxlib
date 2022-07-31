package com.legyver.fenxlib.api.alert;

import com.legyver.fenxlib.api.layout.anchor.AlertAnchor;
import com.legyver.fenxlib.api.layout.anchor.BottomRightAnchor;
import com.legyver.fenxlib.api.layout.anchor.TopRightAnchor;
import javafx.css.Styleable;

/**
 * Tagging interface for an alert
 */
public interface IAlert extends Styleable {

    /**
     * How long to display the alert for.  By default, this is Long.MAX_VALUE
     * @return the timeout in milliseconds
     */
    default long getTimeoutInMillis() {
        return -1;
    }

    /**
     * Get the region of the application to display the alert over.  By default, this is not specified.
     * When not specified, the target region will default to the application-wide defaults configured in
     * {@link com.legyver.fenxlib.api.config.options.ApplicationOptions.Builder#displayAlerts(Level, TargetRegion)}
     * @return the target region
     */
    default TargetRegion getTargetRegion() {
        return null;
    }

    /**
     * The target region to display the alert over
     */
    enum TargetRegion {
        /**
         * The top-left of the application
         */
        APPLICATION_TOP_LEFT(null),
        /**
         * The top-right of the application
         */
        APPLICATION_TOP_RIGHT(new TopRightAnchor()),
        /**
         * The bottom-left of the application
         */
        APPLICATION_BOTTOM_LEFT(null),
        /**
         * The bottom-right of the application
         */
        APPLICATION_BOTTOM_RIGHT(new BottomRightAnchor()),
        /**
         * Over a specified component
         */
        OVER_COMPONENT(null),
        /**
         * In the application status bar
         */
        APPLICATION_STATUS_BAR(null);

        private AlertAnchor alertAnchor;

        TargetRegion(AlertAnchor alertAnchor) {
            this.alertAnchor = alertAnchor;
        }

        /**
         * Get the anchor for the alert region
         * @return the anchor
         */
        public AlertAnchor getAlertAnchor() {
            return alertAnchor;
        }

        /**
         * Override the default anchor by setting your own with custom insets
         * @param alertAnchor the custom alert anchor to use
         */
        public void setAlertAnchor(AlertAnchor alertAnchor) {
            this.alertAnchor = alertAnchor;
        }
    }
}
