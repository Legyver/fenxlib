package com.legyver.fenxlib.locator.query;

import javafx.scene.Node;

import java.util.Optional;

public interface INamedComponentQuery<T extends Node> {

	String getQueryString();
}
