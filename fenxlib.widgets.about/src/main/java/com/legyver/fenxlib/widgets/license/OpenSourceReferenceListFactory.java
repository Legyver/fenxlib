package com.legyver.fenxlib.widgets.license;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;

/**
 * Factory to create an OpenSourceReferenceList
 */
public class OpenSourceReferenceListFactory implements StyleableFactory<OpenSourceReferenceList, OpenSourceReferenceListOptions> {

	@Override
	public OpenSourceReferenceList makeNode(LocationContext locationContext, OpenSourceReferenceListOptions options) throws CoreException {
		return new OpenSourceReferenceList(options.getProperties());
	}

	@Override
	public OpenSourceReferenceListOptions newOptions() {
		return new OpenSourceReferenceListOptions();
	}
}
