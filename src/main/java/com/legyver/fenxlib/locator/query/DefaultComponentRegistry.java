package com.legyver.fenxlib.locator.query;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import com.legyver.fenxlib.locator.IComponentRegistry;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.query.ComponentQuery.NamedComponentQuery;
import com.legyver.fenxlib.locator.query.ComponentQuery.TypedComponentQuery;
import com.legyver.fenxlib.locator.visitor.LocationKeyVisitor;
import com.legyver.util.nippe.Base;
import com.legyver.util.nippe.Step;

public class DefaultComponentRegistry implements IComponentRegistry, QueryableComponentRegistry {

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

	@Override
	public Node get(TypedComponentQuery query) {
		return new Step<>(new Base<>(typedNodes.get(query.getQueryString())),
				typed -> typed.typedNodes.get(query.getType())).execute();
	}

	@Override
	public Node get(NamedComponentQuery query) {
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
