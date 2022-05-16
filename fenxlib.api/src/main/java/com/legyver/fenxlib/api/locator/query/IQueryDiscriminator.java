package com.legyver.fenxlib.api.locator.query;

/**
 * Filter common query results by one of the following strategies
 *   only: when there is only 1 expected result
 *   named: filter on name
 *   typed: filter on expected type
 */
public interface IQueryDiscriminator {
	/**
	 * Query for the only component at the specified location
	 * @return the query
	 */
	ComponentQuery only();

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
	ComponentQuery type(Class type);
}
