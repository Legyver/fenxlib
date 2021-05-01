package com.legyver.fenxlib.core.api.locator;

import com.legyver.fenxlib.core.api.locator.visitor.LocationVisitor;

/**
 * Decorate a location with a sub-location
 */
public class LocationContextDecorator extends AbstractNamedContext implements LocationContext {
	private final LocationContext outerContext;

	/**
	 * Decorate a location context with a sub location context
	 * @param outerContext the context of the parent component
	 */
	public LocationContextDecorator(LocationContext outerContext) {
		super(null);
		this.outerContext = outerContext;
	}

	/**
	 * Get the parent component context
	 * @return the parent context
	 */
	public LocationContext getOuterContext() {
		return outerContext;
	}

	@Override
	public <T> T accept(LocationVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
