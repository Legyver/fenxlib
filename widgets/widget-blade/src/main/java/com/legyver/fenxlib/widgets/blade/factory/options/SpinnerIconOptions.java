package com.legyver.fenxlib.widgets.blade.factory.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.widgets.blade.factory.visitor.IconOptionVisitor;

public class SpinnerIconOptions implements IconWidgetVisitorOptions {
	private final String cssClass;
	private final double radius;

	public SpinnerIconOptions(String cssClass, double radius) {
		this.cssClass = cssClass;
		this.radius = radius;
	}

	public String getCssClass() {
		return cssClass;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public void accept(IconOptionVisitor visitor) throws CoreException {
		visitor.visit(this);
	}


}
