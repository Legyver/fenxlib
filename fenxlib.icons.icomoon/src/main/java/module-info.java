/**
 * Fenxlib IcoMoon icons
 */
module fenxlib.icons.icomoon {
	requires com.legyver.fenxlib.core.api;
	exports com.legyver.fenxlib.icons.icomoon;

	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.icons.icomoon.license.LicenseServiceImpl;
	provides com.legyver.fenxlib.core.api.icons.IconService with com.legyver.fenxlib.icons.icomoon.service.IconServiceImpl;
}