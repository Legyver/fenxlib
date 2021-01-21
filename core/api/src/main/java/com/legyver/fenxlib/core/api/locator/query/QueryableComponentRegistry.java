package com.legyver.fenxlib.core.api.locator.query;

import com.legyver.fenxlib.core.api.locator.IComponentRegistry;
import javafx.scene.Node;

public interface QueryableComponentRegistry extends IComponentRegistry {

	Node get(ITypedComponentQuery query);

	Node get(INamedComponentQuery query);

}
