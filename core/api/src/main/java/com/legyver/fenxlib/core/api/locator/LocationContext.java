package com.legyver.fenxlib.core.api.locator;

import com.legyver.fenxlib.core.api.locator.visitor.LocationVisitor;

public interface LocationContext {
	String getName();

	void setName(String name);

	<T> T accept(LocationVisitor<T> visitor);
}
