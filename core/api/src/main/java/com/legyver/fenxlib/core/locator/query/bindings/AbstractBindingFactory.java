package com.legyver.fenxlib.core.locator.query.bindings;

import com.legyver.fenxlib.core.locator.query.ComponentQuery;

public interface AbstractBindingFactory {

	default ComponentQuery finalizeQuery(ComponentQuery.RegionQueryBuilder query, String named) {
		if (named == null) {
			return query.only();
		} else {
			return query.named(named);
		}
	}

}