package com.legyver.fenxlib.core.locator.visitor;


import com.legyver.fenxlib.core.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;

/**
 * Construct a location key based on concatenating nested contexts
 */
public interface LocationVisitor<T> {

	/**
	 * Retrieve the name of the context
	 * @param context the context
	 * @return the name
	 */
	T visit(DefaultLocationContext context);

	/**
	 * Retrieve the key for the context
	 * @param context the context
	 * @return the key
	 */
	T visit(LocationContextDecorator context);
}
