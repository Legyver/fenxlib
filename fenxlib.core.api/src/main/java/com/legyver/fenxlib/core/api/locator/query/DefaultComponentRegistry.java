package com.legyver.fenxlib.core.api.locator.query;

import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.visitor.LocationKeyVisitor;
import com.legyver.utils.nippe.Base;
import com.legyver.utils.nippe.Step;
import javafx.event.EventTarget;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Component registry handling the registration and querying of components
 */
public class DefaultComponentRegistry implements QueryableComponentRegistry {

	private final Map<String, EventTarget> nodes = new HashMap<>();
	private final Map<String, TypedCtx> typedNodes = new HashMap<>();

	@Override
	public <T extends EventTarget> void register(LocationContext context, T target) {
		String key = context.accept(new LocationKeyVisitor());
		nodes.put(key, target);

		TypedCtx typed = typedNodes.get(key);
		if (typed == null) {
			typed = new TypedCtx();
			typedNodes.put(key, typed);
		}
		typed.typedNodes.put(target.getClass(), target);
	}

	/**
	 * Register a node with a UUID
	 * @param guid the uuid to use
	 * @param node the node to register
	 */
	public void register(UUID guid, Node node) {
		nodes.put(guid.toString(), node);
	}

	/**
	 * Get the node registered under a UUID
	 * @param guid the uuid to reference
	 * @return the node registered with the uuid
	 */
	public <T extends EventTarget>  T get(UUID guid) {
		return (T) nodes.get(guid.toString());
	}

	@Override
	public <T extends EventTarget> T get(ITypedComponentQuery query) {
		return (T) new Step<>(new Base<>(typedNodes.get(query.getQueryString())),
				typed -> typed.typedNodes.get(query.getType())).execute();
	}

	@Override
	public <T extends EventTarget> T get(INamedComponentQuery query) {
		return (T) nodes.get(query.getQueryString());
	}

	@Override
	public void deregister(LocationContext context) {
		String key = context.accept(new LocationKeyVisitor());
		EventTarget node = nodes.remove(key);
		TypedCtx typed = typedNodes.get(key);
		if (typed != null && node != null) {
			typed.typedNodes.remove(node.getClass());
		}
	}

	private class TypedCtx {

		private final Map<Class, EventTarget> typedNodes = new HashMap<>();

	}

}
