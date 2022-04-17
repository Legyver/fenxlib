/**
 * Snackbar widget for the fenxlib framework
 * @since 2.1.1
 */
module com.legyver.fenxlib.widgets.snackbar {
    requires com.legyver.core;
    requires javafx.controls;
    requires org.apache.logging.log4j;
    requires com.legyver.fenxlib.core;

    provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.widgets.snackbar.license.LicenseServiceImpl;
    provides com.legyver.fenxlib.core.alert.AlertService with com.legyver.fenxlib.widgets.snackbar.alert.AlertServiceImpl;

}