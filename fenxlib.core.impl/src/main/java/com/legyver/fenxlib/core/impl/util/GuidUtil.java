package com.legyver.fenxlib.core.impl.util;

import java.util.UUID;
import javafx.scene.Node;

public class GuidUtil {

	private static final String GUID_KEY = "guidKey";

	public static UUID getGuid(Node node) {
		return (UUID) node.getProperties().get(GUID_KEY);
	}

	public static void initGuid(Node node) {
		if (getGuid(node) == null) {
			node.getProperties().put(GUID_KEY, UUID.randomUUID());
		}
	}
}
