package com.legyver.fenxlib.locator.visitor;

import com.legyver.fenxlib.locator.DefaultLocationContext;
import com.legyver.fenxlib.locator.LocationContextDecorator;

public interface LocationVisitor<T> {

	T visit(DefaultLocationContext context);

	T visit(LocationContextDecorator context);
}
