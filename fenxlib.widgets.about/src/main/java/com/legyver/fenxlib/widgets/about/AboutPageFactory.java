package com.legyver.fenxlib.widgets.about;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.utils.graphrunner.PropertyMap;
import com.legyver.utils.propcross.PropertyGraph;
import com.legyver.utils.propcross.SlelOperationContext;

import java.util.Map;
import java.util.Properties;

public class AboutPageFactory implements NodeFactory<AboutPage> {
	private static final String JEXL_VARIABLE = "\\$\\{(([a-z\\.-])*)\\}";
	private final String intro;
	private final String gist;
	private final String additionalInfo;
	private final Properties licenseProperties;
	private final Properties buildProperties;
	private final Properties copyrightProperties;

	public AboutPageFactory(String intro, String gist, String additionalInfo, Properties licenseProperties, Properties buildProperties, Properties copyrightProperties) {
		this.intro = intro;
		this.gist = gist;
		this.additionalInfo = additionalInfo;
		this.licenseProperties = licenseProperties;
		this.buildProperties = buildProperties;
		this.copyrightProperties = copyrightProperties;
	}

	public AboutPageFactory(AboutPageOptions aboutPageOptions) {
		this(aboutPageOptions.getIntro(), aboutPageOptions.getGist(), aboutPageOptions.getAdditionalInfo(), aboutPageOptions.getLicenseProperties(), aboutPageOptions.getBuildProperties(), aboutPageOptions.getCopyrightProperties());
	}

	@Override
	public AboutPage makeNode(LocationContext locationContext) throws CoreException {
		Map<String, Object> map = PropertyMap.of(buildProperties, copyrightProperties);
		PropertyGraph propertyGraph = new PropertyGraph(map);
		propertyGraph.runGraph(new SlelOperationContext(".format"));

		String buildDate = (String) map.get("build.date");
		String version = (String) map.get("build.version");
		String copyright = (String) map.get("copyright");

		return new AboutPage(intro, gist, additionalInfo, version, buildDate, copyright, licenseProperties);
	}
}
