package com.legyver.fenxlib.api.locator.query;

/**
 * Filter common query results by one of the following strategies
 *   only: when there is only 1 expected result
 *   named: filter on name
 *   typed: filter on expected type
 */
public interface IQueryDiscriminator {
	/**
	 * build a query
	 * @return the built query
	 */
	ComponentQuery build();

	/**
	 * Query for the component named a specific value at the specified location
	 * @param name the name of the component
	 * @return the query
	 */
	ComponentQuery named(String name);

	/**
	 * Query for the component of a specific type at the specified location
	 * @param type the type of the component
	 * @return the Query
	 */
	ComponentQuery typed(Class type);
}
