package com.legyver.fenxlib.widgets.license;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.locator.LocationContext;

import java.util.Properties;

/**
 * Factory to create an OpenSourceReferenceList
 */
public class OpenSourceReferenceListFactory implements StyleableFactory<OpenSourceReferenceList> {
	private final Properties properties;

	/**
	 * Construct an OpenSourceReferenceListFactory based on given properties
	 * @param properties properties containing open source references
	 */
	public OpenSourceReferenceListFactory(Properties properties) {
		this.properties = properties;
	}

	@Override
	public OpenSourceReferenceList makeNode(LocationContext locationContext) throws CoreException {
		return new OpenSourceReferenceList(properties);
	}
}
