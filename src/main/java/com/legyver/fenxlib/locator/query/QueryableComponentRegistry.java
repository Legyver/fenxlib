package com.legyver.fenxlib.locator.query;

import com.legyver.fenxlib.locator.query.ComponentQuery.NamedComponentQuery;
import com.legyver.fenxlib.locator.query.ComponentQuery.TypedComponentQuery;
import javafx.scene.Node;

public interface QueryableComponentRegistry {

	Node get(TypedComponentQuery query);

	Node get(NamedComponentQuery query);

}
