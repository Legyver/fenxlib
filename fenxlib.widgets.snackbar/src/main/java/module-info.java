/**
 * Snackbar widget for the fenxlib framework
 * @since 2.1.1
 */
module fenxlib.widgets.snackbar {
    requires com.legyver.core;
    requires com.legyver.fenxlib.core.api;
    requires com.legyver.fenxlib.core.impl;

    provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.widgets.snackbar.license.LicenseServiceImpl;
    provides com.legyver.fenxlib.core.api.alert.AlertService with com.legyver.fenxlib.widgets.snackbar.alert.AlertServiceImpl;

}