import com.legyver.fenxlib.api.alert.AlertFactory;
import com.legyver.fenxlib.api.alert.AlertService;

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
    provides AlertService with com.legyver.fenxlib.widgets.snackbar.alert.AlertServiceImpl;
    provides AlertFactory with com.legyver.fenxlib.widgets.snackbar.alert.DefaultAlertFactory;
    uses AlertFactory;
}