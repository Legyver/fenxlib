package com.legyver.fenxlib.api.locator;

import com.legyver.fenxlib.api.locator.visitor.LocationVisitor;

/**
 * Default LocationContext to register the component as based on the specified name.
 * Typically used as the base (outermost) context.  Widgets inside this widget are expected to decorate the base context.
 */
public class DefaultLocationContext extends AbstractNamedContext implements LocationContext {

	/**
	 * Construct a LocationContext with a specified name
	 * @param name the locally unique name to use
	 */
	public DefaultLocationContext(String name) {
		super(name);
	}

	@Override
	public <T> T accept(LocationVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
