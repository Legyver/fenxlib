package com.legyver.fenxlib.core.alert;

import com.legyver.fenxlib.core.controls.popup.AlertPane;
import javafx.collections.ObservableList;
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
        return Long.MAX_VALUE;
    }

    /**
     * Get the region of the application to display the alert over.  By default, this is Top-Right.
     * @return the target region
     */
    default TargetRegion getTargetRegion() {
        return TargetRegion.TOP_RIGHT;
    }

    /**
     * The target region to display the alert over
     */
    enum TargetRegion {
        /**
         * The top-left of the application
         */
        TOP_LEFT {
            @Override
            public ObservableList<IAlert> getRegionAlerts(AlertPane alertPane) {
                return alertPane.getTopLeftAlerts();
            }
        },
        /**
         * The top-right of the application
         */
        TOP_RIGHT {
            @Override
            public ObservableList<IAlert> getRegionAlerts(AlertPane alertPane) {
                return alertPane.getTopRightAlerts();
            }
        },
        /**
         * The bottom-left of the application
         */
        BOTTOM_LEFT {
            @Override
            public ObservableList<IAlert> getRegionAlerts(AlertPane alertPane) {
                return alertPane.getBottomLeftAlerts();
            }
        },
        /**
         * The bottom-right of the application
         */
        BOTTOM_RIGHT {
            @Override
            public ObservableList<IAlert> getRegionAlerts(AlertPane alertPane) {
                return alertPane.getBottomRightAlerts();
            }
        };

        /**
         * Get the alerts for a region
         * @param alertPane the alert pane
         * @return the alerts for that region
         */
        public abstract ObservableList<IAlert> getRegionAlerts(AlertPane alertPane);
    }
}
