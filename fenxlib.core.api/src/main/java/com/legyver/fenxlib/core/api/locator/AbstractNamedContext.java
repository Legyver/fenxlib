package com.legyver.fenxlib.core.api.locator;

/**
 * Base class for context information about the region in which to register a component
 */
public class AbstractNamedContext {

	private String name;

	/**
	 * Construct a context wrapping the name of the component
	 * @param name the name of the component
	 */
	public AbstractNamedContext(String name) {
		this.name = name;
	}

	/**
	 * Get the name of the context
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the context
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
