package com.legyver.fenxlib.core.layout.options;

import com.legyver.fenxlib.core.controls.factory.NodeFactory;

/**
 * Options for the center of the top bar between the left and right drop-down menus
 */
public class CenterOptions {

	private final NodeFactory factory;

	/**
	 * Use the specified factory to create the node to fill the center region in the menu bar
	 * @param factory the factory to use
	 */
	public CenterOptions(NodeFactory factory) {
		this.factory = factory;
	}

	/**
	 * Get the factory
	 * @return the factory
	 */
	public NodeFactory getFactory() {
		return factory;
	}

}
