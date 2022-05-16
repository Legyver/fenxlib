package com.legyver.fenxlib.api.locator.visitor;

import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;

/**
 * Construct a location key based on concatenating nested contexts
 */
public class LocationKeyVisitor implements LocationVisitor<String> {
	/**
	 * The separator between components of nested contexts
	 * ie: [name]
	 * 	   [parent_name][KEY_SEPARATOR][name]
	 * 	   [grandparent_name][KEY_SEPARATOR][parent_name][KEY_SEPARATOR][name]
	 */
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
