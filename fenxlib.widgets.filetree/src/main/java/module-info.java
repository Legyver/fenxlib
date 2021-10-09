/**
 * FileTree widget for Fenxlib projects
 */
module com.legyver.fenxlib.widgets.filetree {
    requires javafx.base;
    requires javafx.controls;
    requires com.jfoenix;
    requires com.legyver.core;
    requires com.legyver.fenxlib.core.api;
    requires com.legyver.fenxlib.core.impl;
    requires com.legyver.fenxlib.controls.svg;
    requires com.legyver.fenxlib.icons.fa;
    requires com.legyver.fenxlib.extensions.tuktukfx;
    requires com.legyver.tuktukfx;
    requires org.apache.commons.io;

    exports com.legyver.fenxlib.widgets.filetree;
    exports com.legyver.fenxlib.widgets.filetree.factory;
    exports com.legyver.fenxlib.widgets.filetree.nodes;
    exports com.legyver.fenxlib.widgets.filetree.registry;
    exports com.legyver.fenxlib.widgets.filetree.scan;
    exports com.legyver.fenxlib.widgets.filetree.task;
    exports com.legyver.fenxlib.widgets.filetree.tree;

    provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.widgets.filetree.license.LicenseServiceImpl;


}