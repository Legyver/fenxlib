package com.legyver.fenxlib.core.locator.visitor;

import com.legyver.fenxlib.core.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;

public interface LocationVisitor<T> {

	T visit(DefaultLocationContext context);

	T visit(LocationContextDecorator context);
}
