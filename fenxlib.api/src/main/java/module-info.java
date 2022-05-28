import com.legyver.fenxlib.api.io.IOService;

/**
 * API for the fenxlib library
 * @since 3.0
 */
module com.legyver.fenxlib.api {
    requires javafx.base;
    requires javafx.graphics;
    requires com.legyver.core;
    requires com.legyver.utils.nippe;
    requires javafx.controls;
    requires com.legyver.utils.mapqua;
    requires org.apache.logging.log4j;
    exports com.legyver.fenxlib.api;
    exports com.legyver.fenxlib.api.config.parts;
    exports com.legyver.fenxlib.api.regions;
    exports com.legyver.fenxlib.api.context;
    exports com.legyver.fenxlib.api.files;
    exports com.legyver.fenxlib.api.locator;
    exports com.legyver.fenxlib.api.config;
    exports com.legyver.fenxlib.api.service;
    exports com.legyver.fenxlib.api.lifecycle;
    exports com.legyver.fenxlib.api.locator.query;
    exports com.legyver.fenxlib.api.locator.visitor;
    exports com.legyver.fenxlib.api.lifecycle.hooks;
    exports com.legyver.fenxlib.api.uimodel;
    exports com.legyver.fenxlib.api.alert;
    exports com.legyver.fenxlib.api.files.util;
    exports com.legyver.fenxlib.api.config.adapter;
    exports com.legyver.fenxlib.api.logging;
    exports com.legyver.fenxlib.api.config.parts.util;
    exports com.legyver.fenxlib.api.config.options;
    exports com.legyver.fenxlib.api.config.load;
    exports com.legyver.fenxlib.api.io;
    exports com.legyver.fenxlib.api.io.content;
    exports com.legyver.fenxlib.api.files.marshal;
    exports com.legyver.fenxlib.api.files.marshal.contenttype;
    exports com.legyver.fenxlib.api.files.marshal.extension;
    exports com.legyver.fenxlib.api.files.marshal.exception;

    uses IOService;
    uses com.legyver.fenxlib.api.lifecycle.hooks.LifecycleHookService;
}