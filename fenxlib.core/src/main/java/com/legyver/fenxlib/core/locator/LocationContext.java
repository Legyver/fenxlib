package com.legyver.fenxlib.core.locator;

import com.legyver.fenxlib.core.locator.visitor.LocationVisitor;

/**
 * The context that specifies the name to register a component as
 */
public interface LocationContext {
	/**
	 * Get the name to register the component as
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name to register the component as
	 * @param name the name
	 */
	void setName(String name);

	/**
	 * Accept a visitor for construction of the unique key of the component
	 * @param visitor the visitor to accept
	 * @param <T> the type returned by the visitor
	 * @return the visitor result
	 */
	<T> T accept(LocationVisitor<T> visitor);
}
