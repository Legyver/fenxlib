/**
 * Numeric skins for Fenxlib widgets
 */
module com.legyver.fenxlib.skins.number {
	requires javafx.graphics;
	requires com.legyver.fenxlib.core;
	requires javafx.controls;

	exports com.legyver.fenxlib.skins.number;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.skins.number.license.LicenseServiceImpl;
}