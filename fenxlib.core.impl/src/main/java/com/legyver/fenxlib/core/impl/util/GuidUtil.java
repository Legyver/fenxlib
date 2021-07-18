package com.legyver.fenxlib.core.impl.util;

import java.util.UUID;
import javafx.scene.Node;

/**
 * Utility for adding a GUID to a Node.
 */
public class GuidUtil {
	/**
	 * The property name used to store the key on the Node
	 */
	private static final String GUID_KEY = "guidKey";

	/**
	 * Retrieve the GUID from the node's properties
	 * @param node the node on which the GUID is (or is not) set
	 * @return the GUID
	 */
	public static UUID getGuid(Node node) {
		return (UUID) node.getProperties().get(GUID_KEY);
	}

	/**
	 * Generate and add a GUID to the node's properties
	 * @param node the node to add the GUID to
	 */
	public static void initGuid(Node node) {
		if (getGuid(node) == null) {
			node.getProperties().put(GUID_KEY, UUID.randomUUID());
		}
	}
}
