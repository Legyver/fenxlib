/**
 * Blade widget for Fenxlib projects
 */
module com.legyver.fenxlib.widgets.blade {
	requires javafx.base;
	requires javafx.controls;
	requires com.jfoenix;
	requires com.legyver.core;
	requires com.legyver.fenxlib.skins.number;
    requires com.legyver.fenxlib.core;

    exports com.legyver.fenxlib.widgets.blade;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.widgets.blade.license.LicenseServiceImpl;
}