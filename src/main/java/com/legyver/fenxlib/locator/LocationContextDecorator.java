package com.legyver.fenxlib.locator;

import com.legyver.fenxlib.locator.visitor.LocationVisitor;

public class LocationContextDecorator extends AbstractNamedContext implements LocationContext {

	private final LocationContext outerContext;

	public LocationContextDecorator(LocationContext outerContext) {
		this.outerContext = outerContext;
	}

	public LocationContext getOuterContext() {
		return outerContext;
	}

	@Override
	public <T> T accept(LocationVisitor<T> visitor) {
		return visitor.visit(this);
	}

}