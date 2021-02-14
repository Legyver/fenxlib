/**
 * Core API for Fenxlib widgets
 */
module com.legyver.fenxlib.core.api {
	requires transitive javafx.base;
	requires transitive javafx.graphics;
	requires transitive com.legyver.core;
	requires transitive com.legyver.utils.nippe;

	exports com.legyver.fenxlib.core.api.config.options.init;
	exports com.legyver.fenxlib.core.api.context;
	exports com.legyver.fenxlib.core.api.factory;
	exports com.legyver.fenxlib.core.api.locator;
	exports com.legyver.fenxlib.core.api.uimodel;
	exports com.legyver.fenxlib.core.api.util.hook;
	exports com.legyver.fenxlib.core.api.locator.query.bindings;
	exports com.legyver.fenxlib.core.api.locator.query;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.core.api.license.LicenseServiceImpl;
}