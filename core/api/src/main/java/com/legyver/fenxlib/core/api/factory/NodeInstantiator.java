package com.legyver.fenxlib.core.api.factory;

import javafx.scene.Node;

public interface NodeInstantiator<T extends Node> {

	T newInstance();
}
