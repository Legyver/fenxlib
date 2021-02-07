package com.legyver.fenxlib.core.api.locator.visitor;

import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;

public class LocationKeyVisitor implements LocationVisitor<String> {
	public static final String KEY_SEPARATOR = "::";

	@Override
	public String visit(DefaultLocationContext context) {
		return context.getName();
	}

	@Override
	public String visit(LocationContextDecorator context) {
		return context.getOuterContext().accept(this) + KEY_SEPARATOR + context.getName();//traverse outer-context first so keys won't be reversed
	}

}
