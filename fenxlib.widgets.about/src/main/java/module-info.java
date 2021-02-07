/**
 * About Page widget for Fenxlib projects
 */
module fenxlib.widgets.about {
	requires transitive javafx.controls;
	requires transitive com.legyver.core;
	requires transitive com.legyver.fenxlib.core.api;
	requires transitive com.legyver.fenxlib.core.impl;
	requires transitive com.legyver.utils.graphrunner;
	requires transitive com.legyver.utils.slel;
	requires transitive java.desktop;
	requires org.apache.commons.lang3;

	exports com.legyver.fenxlib.widgets.about;
}