/**
 * About Page widget for Fenxlib projects
 */
module fenxlib.widgets.about {
	requires transitive javafx.controls;
	requires transitive com.legyver.fenxlib.core.impl;
	requires transitive com.legyver.utils.propcross;
	requires transitive java.desktop;
	requires org.apache.commons.lang3;

	exports com.legyver.fenxlib.widgets.about;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.widgets.license.service.LicenseServiceImpl;
	uses com.legyver.core.license.LicenseService;
}