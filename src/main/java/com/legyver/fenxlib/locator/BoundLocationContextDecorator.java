package com.legyver.fenxlib.locator;

import javafx.beans.property.StringProperty;

/**
 * Bind a LocationContext to a StringProperty
 */
public class BoundLocationContextDecorator extends LocationContextDecorator implements LocationContext {

	private final StringProperty stringProperty;

	public BoundLocationContextDecorator(LocationContext locationContext, StringProperty stringProperty) {
		super(locationContext);
		this.stringProperty = stringProperty;
	}

	@Override
	public String getName() {
		return stringProperty.get();
	}

}
