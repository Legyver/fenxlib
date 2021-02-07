package com.legyver.fenxlib.widgets.blade.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.widgets.blade.MoreField;

/**
 * Factory for creating "more" fields
 */
public class MoreFieldFactory implements NodeFactory<MoreField> {
	private final String showText;
	private final String hideText;

	/**
	 * Construct a factory to create a "more" field
	 * @param showText the text to display when "more" fields are hidden
	 * @param hideText the text to display when "more" fields are displayed
	 */
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
