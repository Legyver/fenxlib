package com.legyver.fenxlib.core.api.util;

import java.util.UUID;

import com.legyver.fenxlib.core.api.locator.IPropertyAware;
import javafx.scene.Node;

/**
 * Utility for adding a GUID to a Node.
 * @since 2.1 also handles IPropertyAware
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
	public static String getGuid(Node node) {
		return (String) node.getProperties().get(GUID_KEY);
	}

	/**
	 * Retrieve the GUID from the node's properties
	 * @param node the node on which the GUID is (or is not) set
	 * @return the GUID
	 */
	public static String getGuid(IPropertyAware node) {
		return (String) node.getProperties().get(GUID_KEY);
	}

	/**
	 * Generate and add a GUID to the node's properties
	 * Note: This does not set the GUID if a GUID has already been set.
	 * @param node the node to add the GUID to
	 */
	public static void initGuid(Node node) {
		if (getGuid(node) == null) {
			node.getProperties().put(GUID_KEY, UUID.randomUUID().toString());
		}
	}

	/**
	 * Generate and add a GUID to the node's properties
	 * Note: This does not set the GUID if a GUID has already been set.
	 * @param node the node to add the GUID to
	 */
	public static void initGuid(IPropertyAware node) {
		if (getGuid(node) == null) {
			node.getProperties().put(GUID_KEY, UUID.randomUUID().toString());
		}
	}

	/**
	 * Set the GUID on the node's properties.  Unlike {@link #initGuid(Node)}, the GUID is set without regard to if a GUID preexists
	 * @param node the node on whose properties the GUID will be set
	 * @param uuid the GUID to set.
	 */
	public static  void setGuid(Node node, String uuid) {
		node.getProperties().put(GUID_KEY, uuid);
	}

	/**
	 * Set the GUID on the node's properties.  Unlike {@link #initGuid(IPropertyAware)}, the GUID is set without regard to if a GUID preexists
	 * @param node the node on whose properties the GUID will be set
	 * @param uuid the GUID to set.
	 */
	public static  void setGuid(IPropertyAware node, String uuid) {
		node.getProperties().put(GUID_KEY, uuid);
	}
}
