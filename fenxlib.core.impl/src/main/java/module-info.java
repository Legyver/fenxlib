import com.legyver.fenxlib.core.api.icons.service.IconLoaderService;

/**
 * Core Impl for Fenxlib widgets
 */
module com.legyver.fenxlib.core.impl {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.web;
	requires java.desktop;
	requires org.apache.logging.log4j;
	requires org.apache.commons.io;
	requires org.apache.commons.lang3;

	requires transitive com.legyver.fenxlib.core.api;
	requires transitive com.legyver.fenxlib.controls.api;
	requires transitive com.legyver.utils.adaptex;
	requires transitive com.legyver.utils.graphrunner;
	requires transitive com.legyver.utils.jackiso;
	requires transitive com.legyver.utils.mapqua;
	requires transitive com.legyver.utils.nippe;

    exports com.legyver.fenxlib.core.impl.config;
	exports com.legyver.fenxlib.core.impl.config.options;
	exports com.legyver.fenxlib.core.config.options.init;
	exports com.legyver.fenxlib.core.impl.context;
	exports com.legyver.fenxlib.core.impl.files;
	exports com.legyver.fenxlib.core.impl.locator.query.bindings;
	exports com.legyver.fenxlib.core.impl.util;
	exports com.legyver.fenxlib.core.web;

	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.core.impl.license.LicenseServiceImpl;
	provides com.legyver.fenxlib.core.api.config.ConfigService with com.legyver.fenxlib.core.impl.config.ConfigServiceImpl;
	uses IconLoaderService;
}