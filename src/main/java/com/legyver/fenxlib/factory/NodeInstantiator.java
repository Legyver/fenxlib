package com.legyver.fenxlib.factory;

import javafx.scene.Node;

public interface NodeInstantiator<T extends Node> {

	T newInstance();
}
