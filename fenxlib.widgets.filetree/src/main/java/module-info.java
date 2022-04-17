/**
 * FileTree widget for Fenxlib projects
 */
module com.legyver.fenxlib.widgets.filetree {
    requires javafx.base;
    requires javafx.controls;

    requires org.apache.commons.io;
    requires org.apache.logging.log4j;

    requires com.legyver.core;
    requires com.legyver.fenxlib.core;
    requires com.legyver.fenxlib.controls.svg;
    requires com.legyver.fenxlib.extensions.tuktukfx;
    requires com.legyver.tuktukfx;

    exports com.legyver.fenxlib.widgets.filetree;
    exports com.legyver.fenxlib.widgets.filetree.factory;
    exports com.legyver.fenxlib.widgets.filetree.nodes;
    exports com.legyver.fenxlib.widgets.filetree.registry;
    exports com.legyver.fenxlib.widgets.filetree.scan;
    exports com.legyver.fenxlib.widgets.filetree.task;
    exports com.legyver.fenxlib.widgets.filetree.tree;

    provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.widgets.filetree.license.LicenseServiceImpl;
    provides com.legyver.fenxlib.widgets.filetree.service.FileTreeIconService with com.legyver.fenxlib.widgets.filetree.service.DefaultFileTreeIconService;
    uses com.legyver.fenxlib.widgets.filetree.service.FileTreeIconService;
}