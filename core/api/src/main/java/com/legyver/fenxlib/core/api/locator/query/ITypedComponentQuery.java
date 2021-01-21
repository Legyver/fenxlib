package com.legyver.fenxlib.core.api.locator.query;

import javafx.scene.Node;

public interface ITypedComponentQuery<T extends Node> {

	String getQueryString();

	Class getType();
}
