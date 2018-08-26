package com.legyver.fenxlib.util;

import java.util.UUID;
import javafx.scene.Node;

public class GuidUtil {

	private static final String GUID_KEY = "guidKey";

	public static UUID getGuid(Node node, boolean init) {
		UUID guid = getSetGuid(node);
		if (guid == null && init) {
			initGuid(node);
			guid = getSetGuid(node);
		}
		return guid;
	}

	private static UUID getSetGuid(Node node) {
		return (UUID) node.getProperties().get(GUID_KEY);
	}

	public static UUID getGuid(Node node) {
		return getGuid(node, true);
	}

	public static void initGuid(Node node) {
		if (getSetGuid(node) == null) {
			node.getProperties().put(GUID_KEY, UUID.randomUUID());
		}
	}
}
