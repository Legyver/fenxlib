package com.legyver.fenxlib.api.locator;

import com.legyver.fenxlib.api.locator.visitor.LocationVisitor;

import java.util.UUID;

/**
 * The context that specifies the name to register a component as
 */
public interface LocationContext {
	/**
	 * Get the name to register the component as
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name to register the component as
	 * @param name the name
	 */
	void setName(String name);

	/**
	 * Accept a visitor for construction of the unique key of the component
	 * @param visitor the visitor to accept
	 * @param <T> the type returned by the visitor
	 * @return the visitor result
	 */
	<T> T accept(LocationVisitor<T> visitor);

	/**
	 * Decorate a location context
	 * @param name the name of the decorating context.  If name is null, a UUID will be generated
	 * @return the decorated location context
	 */
	default LocationContext decorateWith(String name) {
		if (this.getName() == null || this.getName().isEmpty() || this.getName().isBlank()) {
			return new DefaultLocationContext(name);
		}
		LocationContextDecorator locationContextDecorator = new LocationContextDecorator(this);
		locationContextDecorator.setName(name != null ? name : UUID.randomUUID().toString());
		return locationContextDecorator;
	}
}
