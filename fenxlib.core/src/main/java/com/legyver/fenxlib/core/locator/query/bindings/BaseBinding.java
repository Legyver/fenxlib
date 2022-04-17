package com.legyver.fenxlib.core.locator.query.bindings;

import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.locator.query.IQueryDiscriminator;

/**
 * Base binding mixin that provides query finalization
 */
public abstract class BaseBinding {

	/**
	 * Finalize a query
	 * @param query the query to finalize
	 * @param named the name to append to the query
	 * @return the finalized query
	 */
	protected static ComponentQuery finalizeQuery(IQueryDiscriminator query, String named) {
		if (named == null) {
			return query.only();
		} else {
			return query.named(named);
		}
	}

}
