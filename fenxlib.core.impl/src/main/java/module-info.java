/**
 * Core Impl for Fenxlib widgets
 */
module com.legyver.fenxlib.core.impl {
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
	requires transitive javafx.web;
	requires transitive com.google.gson;
	requires transitive com.legyver.fenxlib.core.api;
	requires transitive com.legyver.utils.adaptex;
	requires transitive com.legyver.utils.graphrunner;
	requires transitive com.legyver.utils.mapqua;
	requires transitive com.legyver.utils.nippe;
	requires transitive com.jfoenix;
	requires transitive org.apache.logging.log4j;
	requires org.apache.commons.io;

	exports com.legyver.fenxlib.core.impl.config;
	exports com.legyver.fenxlib.core.impl.context;
	exports com.legyver.fenxlib.core.impl.factory;
	exports com.legyver.fenxlib.core.impl.files;
	exports com.legyver.fenxlib.core.impl.locator.query.bindings;
	exports com.legyver.fenxlib.core.impl.uimodel;
	exports com.legyver.fenxlib.core.impl.util;
	exports com.legyver.fenxlib.core.impl.widget;
	exports com.legyver.fenxlib.core.impl.config.options;
	exports com.legyver.fenxlib.core.impl.factory.menu;
	exports com.legyver.fenxlib.core.impl.factory.options;
	exports com.legyver.fenxlib.core.impl.factory.decorator;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.core.impl.license.LicenseServiceImpl;
}