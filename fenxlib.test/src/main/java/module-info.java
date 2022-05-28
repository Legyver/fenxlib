/**
 * Base test setup for the fenxlib framework
 */
module com.legyver.fenxlib.tests.base {
    exports com.legyver.fenxlib.tests.base.config;
    exports com.legyver.fenxlib.tests.base.ui;
    exports com.legyver.fenxlib.tests.base;
    requires com.legyver.fenxlib.api;
    requires org.apache.commons.io;
    requires javafx.base;
    requires org.junit.jupiter.api;
    requires org.testfx.junit5;
    requires com.legyver.core;
    requires com.legyver.utils.mapqua;

    provides com.legyver.fenxlib.api.io.IOService with com.legyver.fenxlib.tests.base.io.ClasspathIOService;
}