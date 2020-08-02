package com.legyver.fenxlib.core.widget.license;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.NodeFactory;
import com.legyver.fenxlib.core.locator.LocationContext;

import java.util.Properties;

public class OpenSourceReferenceListFactory implements NodeFactory<OpenSourceReferenceList> {
	private final Properties properties;

	public OpenSourceReferenceListFactory(Properties properties) {
		this.properties = properties;
	}

	@Override
	public OpenSourceReferenceList makeNode(LocationContext locationContext) throws CoreException {
		return new OpenSourceReferenceList(properties);
	}
}
