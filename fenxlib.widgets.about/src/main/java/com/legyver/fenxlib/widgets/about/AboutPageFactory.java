package com.legyver.fenxlib.widgets.about;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.utils.graphrunner.*;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.utils.graphrunner.ctx.shared.SharedContextCommand;
import com.legyver.utils.graphrunner.ctx.shared.SharedMapCtx;
import com.legyver.utils.slel.ExpressionInterpreter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		Pattern jexlVar = Pattern.compile(JEXL_VARIABLE);
		VariableExtractionOptions variableExtractionOptions = new VariableExtractionOptions(jexlVar, 1);
		VariableTransformationRule variableTransformationRule = new VariableTransformationRule(Pattern.compile("\\.format$"),
				TransformationOperation.upToLastIndexOf(".format"));
		PropertyGraphFactory factory = new PropertyGraphFactory(variableExtractionOptions, variableTransformationRule);
		Graph<SharedMapCtx> contextGraph = factory.make(map, (s, o) -> new SharedMapCtx(s, map));

		contextGraph.executeStrategy(new SharedContextCommand() {
			@Override
			public void executeString(String nodeName, String currentValue) {
				Matcher m = jexlVar.matcher(currentValue);
				if (m.find()) {
					ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter(map);
					String value = (String) expressionInterpreter.evaluate(currentValue);
					//update the map with the value
					String key = nodeName;
					//avoid overwriting the .format property
					if (variableTransformationRule.matches(nodeName)) {
						key = variableTransformationRule.transform(nodeName);
					}
					map.put(key, value);
				}
			}
		});

		String buildDate = (String) map.get("build.date");
		String version = (String) map.get("build.version");
		String copyright = (String) map.get("copyright");

		return new AboutPage(intro, gist, additionalInfo, version, buildDate, copyright, licenseProperties);
	}
}
