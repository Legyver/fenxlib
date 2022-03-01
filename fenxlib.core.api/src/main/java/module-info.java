/**
 * Core API for Fenxlib widgets
 */
module com.legyver.fenxlib.core.api {
	requires javafx.base;
	requires javafx.graphics;
	requires transitive com.legyver.core;
	requires transitive com.legyver.utils.adaptex;
	requires transitive com.legyver.utils.nippe;
	requires transitive org.apache.logging.log4j;
	requires javafx.controls;

	exports com.legyver.fenxlib.core.api.alert;
	exports com.legyver.fenxlib.core.api.config;
	exports com.legyver.fenxlib.core.api.config.parts;
	exports com.legyver.fenxlib.core.api.config.options.init;
	exports com.legyver.fenxlib.core.api.context;
	exports com.legyver.fenxlib.core.api.event.correlation;
	exports com.legyver.fenxlib.core.api.event.handlers;
	exports com.legyver.fenxlib.core.api.factory;
	exports com.legyver.fenxlib.core.api.icons;
	exports com.legyver.fenxlib.core.api.locator;
	exports com.legyver.fenxlib.core.api.uimodel;
	exports com.legyver.fenxlib.core.api.util;
	exports com.legyver.fenxlib.core.api.util.hook;
	exports com.legyver.fenxlib.core.api.locator.query.bindings;
	exports com.legyver.fenxlib.core.api.locator.query;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.core.api.license.LicenseServiceImpl;
	uses com.legyver.fenxlib.core.api.config.ConfigService;

}