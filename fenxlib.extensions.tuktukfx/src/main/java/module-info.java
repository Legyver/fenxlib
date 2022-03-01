/**
 * Fenxlib extension for TukTukFX
 */
module com.legyver.fenxlib.extensions.tuktukfx {
	requires com.legyver.fenxlib.core.api;
	requires com.legyver.tuktukfx;
	requires org.apache.logging.log4j;
	requires javafx.graphics;

	exports com.legyver.fenxlib.extensions.tuktukfx.bindings;
	exports com.legyver.fenxlib.extensions.tuktukfx.config;
	exports com.legyver.fenxlib.extensions.tuktukfx.task;
	exports com.legyver.fenxlib.extensions.tuktukfx.task.adapter;
	exports com.legyver.fenxlib.extensions.tuktukfx.task.exec;
	provides com.legyver.core.license.LicenseService with com.legyver.fenxlib.extensions.tuktukfx.license.LicenseServiceImpl;
}