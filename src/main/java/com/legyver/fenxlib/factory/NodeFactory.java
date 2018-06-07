package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import javafx.scene.Node;
import com.legyver.fenxlib.locator.LocationContext;

public interface NodeFactory<T extends Node> {

	T makeNode(LocationContext locationContext) throws CoreException;
}
