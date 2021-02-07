package com.legyver.fenxlib.core.api.locator.visitor;

import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;

public interface LocationVisitor<T> {

	T visit(DefaultLocationContext context);

	T visit(LocationContextDecorator context);
}
