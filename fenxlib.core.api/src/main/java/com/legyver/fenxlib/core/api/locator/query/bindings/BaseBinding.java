package com.legyver.fenxlib.core.api.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.IQueryDiscriminator;
import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;

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
