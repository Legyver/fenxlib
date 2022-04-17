import com.legyver.fenxlib.controls.icon.license.LicenseServiceImpl;

/**
 * Fenxlib SVG icon control skins
 */
module com.legyver.fenxlib.controls.svg {
	requires javafx.graphics;
	requires com.legyver.fenxlib.core;
	requires javafx.controls;
    requires org.apache.logging.log4j;

    exports com.legyver.fenxlib.controls.icon;
    provides com.legyver.core.license.LicenseService with LicenseServiceImpl;
}