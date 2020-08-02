package com.legyver.fenxlib.core.widget.about;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.NodeFactory;
import com.legyver.util.graphrunner.*;
import com.legyver.fenxlib.core.locator.LocationContext;
import org.apache.commons.jexl3.*;

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
		ContextGraphFactory factory = new ContextGraphFactory(variableExtractionOptions, variableTransformationRule);
		ContextGraph contextGraph = factory.make(map);

		JexlEngine jexl = new JexlBuilder().create();
		JexlContext context = new MapContext(map);
		GraphRunner runner = new GraphRunner(map);
		runner.setCommand((nodeName, currentValue) -> {
			try {
				Matcher m = jexlVar.matcher((String) currentValue);
				if (m.find()) {
					JexlExpression expression = jexl.createExpression((String) currentValue);
					String value = (String) expression.evaluate(context);
					//update the map with the value
					String key = nodeName;
					//avoid overwriting the .format property
					if (variableTransformationRule.matches(nodeName)) {
						key = variableTransformationRule.transform(nodeName);
					}
					map.put(key, value);
				}
			} catch (RuntimeException ex) {
				throw new CoreException("Error evaluating expression: " + currentValue, ex);
			}
		});
		runner.runGraph(contextGraph);

		String buildDate = (String) map.get("build.date");
		String version = (String) map.get("build.version");
		String copyright = (String) map.get("copyright");

		return new AboutPage(intro, gist, additionalInfo, version, buildDate, copyright, licenseProperties);
	}


	private String evaluate(JexlEngine jexl, JexlContext context, String expression) throws CoreException {
		try {
			JexlExpression e = jexl.createExpression(expression);
			return (String) e.evaluate(context);
		} catch (RuntimeException ex) {
			throw new CoreException("Error evaluating expression: " + expression, ex);
		}
	}

	private void setPropertiesOnContext(JexlContext context, Properties properties) {
		for (String key : properties.stringPropertyNames()) {
			String value = (String) properties.get(key);
			context.set(key, value);
		}
	}
}
