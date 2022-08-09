package com.legyver.fenxlib.widgets.about;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.utils.graphrunner.PropertyMap;
import com.legyver.utils.propcross.PropertyGraph;
import com.legyver.utils.propcross.SlelOperationContext;

import java.util.Map;

/**
 * Factory to create an AboutPage
 */
public class AboutPageFactory implements StyleableFactory<AboutPage, AboutPageOptions> {

	@Override
	public AboutPage makeNode(LocationContext locationContext, AboutPageOptions aboutPageOptions) throws CoreException {
		Map<String, Object> map = PropertyMap.of(aboutPageOptions.getBuildProperties(), aboutPageOptions.getCopyrightProperties());
		PropertyGraph propertyGraph = new PropertyGraph(map);
		propertyGraph.runGraph(new SlelOperationContext(".format"));

		String buildDate = (String) map.get("build.date");
		String version = (String) map.get("build.version");
		String copyright = (String) map.get("copyright");

		return new AboutPage(
				aboutPageOptions.getIntro(),
				aboutPageOptions.getGist(),
				aboutPageOptions.getAdditionalInfo(),
				version, buildDate, copyright,
				aboutPageOptions.getLicenseProperties()
		);
	}

	@Override
	public AboutPageOptions newOptions() {
		return new AboutPageOptions.Builder(null).build();
	}
}
