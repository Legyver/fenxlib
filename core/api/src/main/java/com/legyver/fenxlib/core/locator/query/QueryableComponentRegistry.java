package com.legyver.fenxlib.core.locator.query;

import com.legyver.fenxlib.core.locator.IComponentRegistry;
import javafx.scene.Node;

public interface QueryableComponentRegistry extends IComponentRegistry {

	Node get(ITypedComponentQuery query);

	Node get(INamedComponentQuery query);

}
