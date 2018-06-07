package com.legyver.fenxlib.locator.query;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import com.legyver.fenxlib.locator.IComponentRegistry;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.visitor.LocationKeyVisitor;

public class DefaultComponentRegistry implements IComponentRegistry, QueryableComponentRegistry {
	private final Map<String, Node> nodes = new HashMap<>();

	@Override
	public void register(LocationContext context, Node node) {
		String key = context.accept(new LocationKeyVisitor());
		nodes.put(key, node);
	}

	@Override
	public Node get(String s) {
		return nodes.get(s);
	}

}
