/**
 * Fenxlib SVG icon control skins
 */
module com.legyver.fenxlib.controls.svg {
	requires com.jfoenix;
	requires javafx.graphics;
	requires com.legyver.fenxlib.core.api;
	requires javafx.controls;

	exports com.legyver.fenxlib.controls.svg;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.controls.svg.license.LicenseServiceImpl;
}