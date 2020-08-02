package com.legyver.fenxlib.core.locator;

import com.legyver.fenxlib.core.locator.visitor.LocationVisitor;

public class DefaultLocationContext extends AbstractNamedContext implements LocationContext {

	public DefaultLocationContext(String name) {
		super(name);
	}

	@Override
	public <T> T accept(LocationVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
