package com.legyver.fenxlib.locator;

import javafx.scene.Node;

public interface IComponentRegistry {

	void register(LocationContext context, Node node);
	void deregister(LocationContext context);
}
