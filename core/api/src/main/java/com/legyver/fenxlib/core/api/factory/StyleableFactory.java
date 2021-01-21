package com.legyver.fenxlib.core.api.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.css.Styleable;

public interface StyleableFactory<T extends Styleable> {
	T makeNode(LocationContext locationContext) throws CoreException;
}
