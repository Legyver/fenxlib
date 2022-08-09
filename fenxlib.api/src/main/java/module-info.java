import com.legyver.fenxlib.api.alert.AlertService;
import com.legyver.fenxlib.api.controls.service.NodeInstantiationService;
import com.legyver.fenxlib.api.i18n.ResourceBundleService;
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
    requires org.apache.commons.lang3;
    requires javafx.web;

    exports com.legyver.fenxlib.api;
    exports com.legyver.fenxlib.api.alert;
    exports com.legyver.fenxlib.api.config;
    exports com.legyver.fenxlib.api.config.adapter;
    exports com.legyver.fenxlib.api.config.load;
    exports com.legyver.fenxlib.api.config.options;
    exports com.legyver.fenxlib.api.config.parts;
    exports com.legyver.fenxlib.api.config.parts.util;
    exports com.legyver.fenxlib.api.context;
    exports com.legyver.fenxlib.api.controls;
    exports com.legyver.fenxlib.api.controls.builder;
    exports com.legyver.fenxlib.api.controls.builder.mixin;
    exports com.legyver.fenxlib.api.controls.factory;
    exports com.legyver.fenxlib.api.controls.factory.adapters;
    exports com.legyver.fenxlib.api.controls.options;
    exports com.legyver.fenxlib.api.controls.service;
    exports com.legyver.fenxlib.api.controls.utils;
    exports com.legyver.fenxlib.api.files;
    exports com.legyver.fenxlib.api.files.marshal;
    exports com.legyver.fenxlib.api.files.marshal.contenttype;
    exports com.legyver.fenxlib.api.files.marshal.exception;
    exports com.legyver.fenxlib.api.files.marshal.extension;
    exports com.legyver.fenxlib.api.files.util;
    exports com.legyver.fenxlib.api.i18n;
    exports com.legyver.fenxlib.api.icons.application;
    exports com.legyver.fenxlib.api.icons.exception;
    exports com.legyver.fenxlib.api.icons.fonts;
    exports com.legyver.fenxlib.api.icons.options;
    exports com.legyver.fenxlib.api.io;
    exports com.legyver.fenxlib.api.io.content;
    exports com.legyver.fenxlib.api.layout;
    exports com.legyver.fenxlib.api.layout.anchor;
    exports com.legyver.fenxlib.api.lifecycle;
    exports com.legyver.fenxlib.api.lifecycle.hooks;
    exports com.legyver.fenxlib.api.locator;
    exports com.legyver.fenxlib.api.locator.query;
    exports com.legyver.fenxlib.api.locator.visitor;
    exports com.legyver.fenxlib.api.logging;
    exports com.legyver.fenxlib.api.regions;
    exports com.legyver.fenxlib.api.scene.controls.options;
    exports com.legyver.fenxlib.api.scene.layout.options;
    exports com.legyver.fenxlib.api.scene.text.options;
    exports com.legyver.fenxlib.api.scene.web.options;
    exports com.legyver.fenxlib.api.service;
    exports com.legyver.fenxlib.api.uimodel;

    //for reflection in Options Mixins
    opens com.legyver.fenxlib.api.scene.controls.options to org.apache.commons.lang3;
    opens com.legyver.fenxlib.api.scene.layout.options to org.apache.commons.lang3;
    opens com.legyver.fenxlib.api.scene.text.options to org.apache.commons.lang3;
    opens com.legyver.fenxlib.api.scene.web.options to org.apache.commons.lang3;

    provides com.legyver.fenxlib.api.i18n.ResourceBundleService with com.legyver.fenxlib.api.i18n.ApplicationOptionsResourceBundleServiceImpl;

    uses AlertService;
    uses IOService;
    uses NodeInstantiationService;
    uses ResourceBundleService;
    uses com.legyver.fenxlib.api.lifecycle.hooks.LifecycleHookService;
    uses com.legyver.fenxlib.api.config.ConfigService;
}