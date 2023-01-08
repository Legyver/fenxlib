/**
 * About Page widget for Fenxlib projects
 */
module com.legyver.fenxlib.widgets.about {
	requires javafx.controls;
	requires transitive com.legyver.fenxlib.core;
	requires transitive com.legyver.utils.propcross;
	requires java.desktop;
	requires org.apache.commons.lang3;
	requires org.apache.logging.log4j;

	exports com.legyver.fenxlib.widgets.about;
	exports com.legyver.fenxlib.widgets.license;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.widgets.license.service.LicenseServiceImpl;
	provides com.legyver.fenxlib.api.i18n.ResourceBundleService with com.legyver.fenxlib.widgets.about.i18n.ResourceBundleServiceImpl;
}