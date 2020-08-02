package com.legyver.fenxlib.widgets.blade.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.NodeFactory;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.widgets.blade.MoreField;

public class MoreFieldFactory implements NodeFactory<MoreField> {
	private final String showText;
	private final String hideText;

	public MoreFieldFactory(String showText, String hideText) {
		this.showText = showText;
		this.hideText = hideText;
	}
	
	@Override
	public MoreField makeNode(LocationContext locationContext) throws CoreException {
		MoreField moreField = new MoreField(showText, hideText);
		return moreField;
	}

}
