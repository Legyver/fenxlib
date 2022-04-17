/**
 * Core API for Fenxlib widgets
 */
module com.legyver.fenxlib.core {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.web;

	requires org.apache.commons.io;
	requires org.apache.logging.log4j;
	requires java.desktop;

	requires transitive com.legyver.core;
	requires transitive com.legyver.utils.adaptex;
	requires transitive com.legyver.utils.graphrunner;
	requires transitive com.legyver.utils.mapqua;
	requires transitive com.legyver.utils.nippe;

    exports com.legyver.fenxlib.core;
	exports com.legyver.fenxlib.core.alert;
	exports com.legyver.fenxlib.core.config;
	exports com.legyver.fenxlib.core.config.load;
	exports com.legyver.fenxlib.core.config.options;
	exports com.legyver.fenxlib.core.config.parts;
	exports com.legyver.fenxlib.core.context;
	exports com.legyver.fenxlib.core.controls.factory;
	exports com.legyver.fenxlib.core.controls.popup;
	exports com.legyver.fenxlib.core.event.correlation;
	exports com.legyver.fenxlib.core.event.handlers;
	exports com.legyver.fenxlib.core.files;
	exports com.legyver.fenxlib.core.icons;
	exports com.legyver.fenxlib.core.icons.options;
	exports com.legyver.fenxlib.core.icons.service;
	exports com.legyver.fenxlib.core.layout.factory;
	exports com.legyver.fenxlib.core.layout.options;
	exports com.legyver.fenxlib.core.lifecycle;
	exports com.legyver.fenxlib.core.lifecycle.hooks;
	exports com.legyver.fenxlib.core.locator;
	exports com.legyver.fenxlib.core.locator.query;
	exports com.legyver.fenxlib.core.locator.query.bindings;
	exports com.legyver.fenxlib.core.locator.visitor;
	exports com.legyver.fenxlib.core.menu.factory;
	exports com.legyver.fenxlib.core.menu.options;
	exports com.legyver.fenxlib.core.menu.section;
	exports com.legyver.fenxlib.core.menu.templates;
	exports com.legyver.fenxlib.core.menu.templates.file;
	exports com.legyver.fenxlib.core.menu.templates.section;
	exports com.legyver.fenxlib.core.uimodel;
	exports com.legyver.fenxlib.core.util;
	exports com.legyver.fenxlib.core.controls;
    exports com.legyver.fenxlib.core.files.action;
    exports com.legyver.fenxlib.core.files.action.internal;
	exports com.legyver.fenxlib.core.web;
	exports com.legyver.fenxlib.core.layout;
	exports com.legyver.fenxlib.core.controls.decorator;
	exports com.legyver.fenxlib.core.service;

	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.core.license.LicenseServiceImpl;
	provides com.legyver.fenxlib.core.config.ConfigService with com.legyver.fenxlib.core.config.ConfigServiceImpl;
	provides com.legyver.fenxlib.core.files.marshal.FileMarshal with com.legyver.fenxlib.core.files.marshal.contenttype.ApplicationJsonContentTypeFileMarshal;

	uses com.legyver.fenxlib.core.config.ConfigService;
	uses com.legyver.fenxlib.core.icons.service.IconLoaderService;
	uses com.legyver.fenxlib.core.files.marshal.FileMarshal;
}