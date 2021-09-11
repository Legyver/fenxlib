/**
 * Fenxlib FontAwesome icons
 */
module com.legyver.fenxlib.icons.fa {
	requires com.legyver.fenxlib.core.api;
	exports com.legyver.fenxlib.icons.fa;

	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.icons.fa.license.LicenseServiceImpl;
	provides com.legyver.fenxlib.core.api.icons.IconService with com.legyver.fenxlib.icons.fa.service.IconServiceImpl;
}