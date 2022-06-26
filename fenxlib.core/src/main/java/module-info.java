import com.legyver.fenxlib.api.files.marshal.FileMarshalService;

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
	requires transitive com.legyver.fenxlib.api;
    requires com.legyver.utils.mapadapt;
    requires org.apache.commons.lang3;

    exports com.legyver.fenxlib.core.context;
	exports com.legyver.fenxlib.core.controls;
	exports com.legyver.fenxlib.core.controls.builder;
	exports com.legyver.fenxlib.core.controls.factory;
	exports com.legyver.fenxlib.core.controls.options;
	exports com.legyver.fenxlib.core.controls.popup;
	exports com.legyver.fenxlib.core.controls.service;
	exports com.legyver.fenxlib.core.event.correlation;
	exports com.legyver.fenxlib.core.event.handlers;
	exports com.legyver.fenxlib.core.factory.adapters;
	exports com.legyver.fenxlib.core.files.action;
	exports com.legyver.fenxlib.core.files.action.internal;
	exports com.legyver.fenxlib.core.files.util;
	exports com.legyver.fenxlib.core.icons;
	exports com.legyver.fenxlib.core.icons.options;
	exports com.legyver.fenxlib.core.icons.service;
	exports com.legyver.fenxlib.core.layout;
	exports com.legyver.fenxlib.core.layout.factory;
	exports com.legyver.fenxlib.core.layout.options;
	exports com.legyver.fenxlib.core.lifecycle;
	exports com.legyver.fenxlib.core.lifecycle.hooks;
	exports com.legyver.fenxlib.core.locator;
	exports com.legyver.fenxlib.core.locator.query;
	exports com.legyver.fenxlib.core.locator.query.bindings;
	exports com.legyver.fenxlib.core.menu.factory;
	exports com.legyver.fenxlib.core.menu.options;
	exports com.legyver.fenxlib.core.menu.section;
	exports com.legyver.fenxlib.core.menu.templates;
	exports com.legyver.fenxlib.core.menu.templates.file;
	exports com.legyver.fenxlib.core.menu.templates.section;
	exports com.legyver.fenxlib.core.scene.controls.factory;
	exports com.legyver.fenxlib.core.scene.controls.options;
	exports com.legyver.fenxlib.core.scene.layout.factory;
	exports com.legyver.fenxlib.core.scene.layout.options;
	exports com.legyver.fenxlib.core.scene.text.factory;
	exports com.legyver.fenxlib.core.scene.text.options;
	exports com.legyver.fenxlib.core.scene.web.factory;
	exports com.legyver.fenxlib.core.scene.web.options;
	exports com.legyver.fenxlib.core.util;
    exports com.legyver.fenxlib.core.web;
	exports com.legyver.fenxlib.core.util.map;

	//for reflection in Options Mixins
	opens com.legyver.fenxlib.core.scene.controls.options to org.apache.commons.lang3;
	opens com.legyver.fenxlib.core.scene.layout.options to org.apache.commons.lang3;
	opens com.legyver.fenxlib.core.scene.text.options to org.apache.commons.lang3;
	opens com.legyver.fenxlib.core.scene.web.options to org.apache.commons.lang3;

	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.core.license.LicenseServiceImpl;
	provides com.legyver.fenxlib.api.lifecycle.hooks.LifecycleHookService with com.legyver.fenxlib.core.lifecycle.hooks.LifecycleHookServiceImpl;
	provides com.legyver.fenxlib.api.io.IOService with com.legyver.fenxlib.core.io.DiskFileIoService;
	provides com.legyver.fenxlib.core.controls.service.NodeInstantiationService with com.legyver.fenxlib.core.controls.service.DefaultNodeInstantiationService;
	provides FileMarshalService with com.legyver.fenxlib.core.files.marshal.contenttype.ApplicationJsonContentTypeFileMarshal;


	uses com.legyver.fenxlib.api.config.ConfigService;
	uses com.legyver.fenxlib.core.controls.service.NodeInstantiationService;
	uses com.legyver.fenxlib.core.icons.service.IconLoaderService;
	uses FileMarshalService;

}