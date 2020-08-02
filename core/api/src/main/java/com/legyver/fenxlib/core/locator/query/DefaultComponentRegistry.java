package com.legyver.fenxlib.core.locator.query;

import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.visitor.LocationKeyVisitor;
import com.legyver.util.nippe.Base;
import com.legyver.util.nippe.Step;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DefaultComponentRegistry implements QueryableComponentRegistry {

	private final Map<String, Node> nodes = new HashMap<>();
	private final Map<String, TypedCtx> typedNodes = new HashMap<>();

	@Override
	public void register(LocationContext context, Node node) {
		String key = context.accept(new LocationKeyVisitor());
		nodes.put(key, node);

		TypedCtx typed = typedNodes.get(key);
		if (typed == null) {
			typed = new TypedCtx();
			typedNodes.put(key, typed);
		}
		typed.typedNodes.put(node.getClass(), node);
	}
	
	public void register(UUID guid, Node node) {
		nodes.put(guid.toString(), node);
	}
	
	public Node get(UUID guid) {
		return nodes.get(guid.toString());
	}

	@Override
	public Node get(ITypedComponentQuery query) {
		return new Step<>(new Base<>(typedNodes.get(query.getQueryString())),
				typed -> typed.typedNodes.get(query.getType())).execute();
	}

	@Override
	public Node get(INamedComponentQuery query) {
		return nodes.get(query.getQueryString());
	}

	@Override
	public void deregister(LocationContext context) {
		String key = context.accept(new LocationKeyVisitor());
		Node node = nodes.remove(key);
		TypedCtx typed = typedNodes.get(key);
		if (typed != null && node != null) {
			typed.typedNodes.remove(node.getClass());
		}
	}

	private class TypedCtx {

		private final Map<Class, Node> typedNodes = new HashMap<>();

	}

}
