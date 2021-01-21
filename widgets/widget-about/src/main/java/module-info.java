/**
 * About Page widget for Fenxlib projects
 */
module fenxlib.widgets.widget.about {
	requires transitive javafx.controls;
	requires transitive org.apache.commons.lang3;
	requires transitive com.legyver.fenxlib.core.impl;
	requires transitive com.legyver.core;
	requires transitive com.legyver.fenxlib.core.api;
	requires transitive com.legyver.utils.graphrunner;
	requires transitive java.desktop;
	requires transitive com.legyver.utils.slel;

	exports com.legyver.fenxlib.widget.about;
}