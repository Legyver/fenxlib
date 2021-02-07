package com.legyver.fenxlib.widgets.blade.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.IconOptionVisitor;

/**
 * Options describing the CSS class and size of a spinner
 */
public class SpinnerIconOptions implements IconWidgetVisitorOptions {
	private final String cssClass;
	private final double radius;

	/**
	 * Construct options on how to render a spinner
	 * @param cssClass the css class to use
	 * @param radius the radius of the spinner
	 */
	public SpinnerIconOptions(String cssClass, double radius) {
		this.cssClass = cssClass;
		this.radius = radius;
	}

	/**
	 * Get the css class of the spinner
	 * @return the css class
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * Get the radius of the spinner
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public void accept(IconOptionVisitor visitor) throws CoreException {
		visitor.visit(this);
	}


}
