/**
 * Core Impl for Fenxlib widgets
 */
module com.legyver.fenxlib.core.impl {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.web;
	requires transitive com.legyver.fenxlib.core.api;
	requires transitive com.legyver.utils.adaptex;
	requires transitive com.legyver.utils.graphrunner;
	requires transitive com.legyver.utils.jackiso;
	requires transitive com.legyver.utils.mapqua;
	requires transitive com.legyver.utils.nippe;
	requires com.jfoenix;
	requires org.apache.logging.log4j;
	requires org.apache.commons.io;
	requires org.apache.commons.lang3;
    requires java.desktop;

    exports com.legyver.fenxlib.core.impl.config;
	exports com.legyver.fenxlib.core.impl.config.options;
	exports com.legyver.fenxlib.core.impl.config.options.init;
	exports com.legyver.fenxlib.core.impl.context;
	exports com.legyver.fenxlib.core.impl.factory;
	exports com.legyver.fenxlib.core.impl.factory.menu;
	exports com.legyver.fenxlib.core.impl.factory.menu.file;
	exports com.legyver.fenxlib.core.impl.factory.menu.file.proto;
	exports com.legyver.fenxlib.core.impl.factory.options;
	exports com.legyver.fenxlib.core.impl.factory.decorator;
	exports com.legyver.fenxlib.core.impl.files;
	exports com.legyver.fenxlib.core.impl.icons;
	exports com.legyver.fenxlib.core.impl.locator.query.bindings;
	exports com.legyver.fenxlib.core.impl.uimodel;
	exports com.legyver.fenxlib.core.impl.util;
	exports com.legyver.fenxlib.core.impl.web;

	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.core.impl.license.LicenseServiceImpl;
	provides com.legyver.fenxlib.core.api.config.ConfigService with com.legyver.fenxlib.core.impl.config.ConfigServiceImpl;
	uses com.legyver.fenxlib.core.api.icons.IconService;
}