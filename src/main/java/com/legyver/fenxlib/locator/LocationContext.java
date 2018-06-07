package com.legyver.fenxlib.locator;

import com.legyver.fenxlib.locator.visitor.LocationVisitor;

public interface LocationContext {
	String getName();

	void setName(String name);

	<T> T accept(LocationVisitor<T> visitor);
}
