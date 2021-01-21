package com.legyver.fenxlib.widgets.blade.factory.blade;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.AbstractGridPaneLayoutVisitor;

/**
 * Layout option for
 *   &lt;"more"&gt;
 */
public class ShowMoreLabelOption implements BladeOption {
	private final String showText;
	private final String hideText;
	private final String cssClass;

	/**
	 * Construct the option for the "more" field
	 * @param text the "show" text
	 * @param registerAs what to register the "more" element as
	 * @param cssClass css class to use
	 */
	public ShowMoreLabelOption(String text, String registerAs, String cssClass) {
		this.showText = text;
		this.hideText = registerAs;
		this.cssClass = cssClass;
	}

	/**
	 * Construct the option for the "more" field.  The default cssClass will be "more-label"
	 * @param text the "show" text
	 * @param registerAs what to register the "more" element as
	 */
	public ShowMoreLabelOption(String text, String registerAs) {
		this(text, registerAs, "more-label");
	}

	@Override
	public void accept(AbstractGridPaneLayoutVisitor visitor, int row) throws CoreException {
		visitor.visit(this, row);
	}

	/**
	 * Get the text to show for the "show" option
	 * @return show text
	 */
	public String getShowText() {
		return showText;
	}

	/**
	 * Get the Css class to style the "more" text
	 * @return the css class
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * Get the text to show for the hide option
	 * @return hide text
	 */
	public String getHideText() {
		return hideText;
	}

}
