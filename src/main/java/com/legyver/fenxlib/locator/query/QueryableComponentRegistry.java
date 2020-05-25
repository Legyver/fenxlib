package com.legyver.fenxlib.locator.query;

import javafx.scene.Node;

public interface QueryableComponentRegistry {

	Node get(ITypedComponentQuery query);

	Node get(INamedComponentQuery query);

}
