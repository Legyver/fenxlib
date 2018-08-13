package com.legyver.fenxlib.factory.options.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.visitor.AbstractGridPaneLayoutVisitor;

public class ShowMoreLabelOption implements BladeOption {
	private final String showText;
	private final String hideText;
	private final String cssClass;
	
	public ShowMoreLabelOption(String text, String registerAs, String cssClass) {
		this.showText = text;
		this.hideText = registerAs;
		this.cssClass = cssClass;
	}
	
	public ShowMoreLabelOption(String text, String registerAs) {
		this(text, registerAs, "more-label");
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}

	public String getShowText() {
		return showText;
	}

	public String getCssClass() {
		return cssClass;
	}

	public String getHideText() {
		return hideText;
	}

}
