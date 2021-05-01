package com.legyver.fenxlib.core.impl.factory;

import javafx.beans.property.StringProperty;

/**
 * Factory that exposes a bound property of the underlying node
 */
public interface BindableValueFactory {

	/**
	 * Get the bound property
	 * @return the bound property
	 */
	StringProperty boundProperty();
}
