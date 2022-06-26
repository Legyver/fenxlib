package com.legyver.fenxlib.core.locator.query;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.query.INamedComponentQuery;
import com.legyver.fenxlib.api.locator.query.ITypedComponentQuery;
import com.legyver.fenxlib.api.locator.query.QueryableComponentRegistry;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.visitor.LocationKeyVisitor;
import com.legyver.fenxlib.core.locator.IPropertyAware;
import com.legyver.fenxlib.core.util.GuidUtil;
import com.legyver.fenxlib.core.util.PropertyMapExtractor;
import com.legyver.utils.nippe.Base;
import com.legyver.utils.nippe.Step;
import javafx.collections.ObservableMap;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

import java.util.*;

/**
 * Component registry handling the registration and querying of components.
 * @since 2.1: Sets the location context key on the Node as a Guid using {@link GuidUtil}
 * @since 1.0
 */
public class DefaultComponentRegistry implements QueryableComponentRegistry {

	private final Map<String, EventTarget> nodes = new HashMap<>();
	private final Map<String, TypedCtx> typedNodes = new HashMap<>();

	@Override
	public <T extends EventTarget> void register(LocationContext context, T target, boolean typedOnly) {
		ObservableMap<Object, Object> propertyMap = new PropertyMapExtractor(target).get();
		String key;
		if (context == null) {
			key = "";
		} else {
			key = context.accept(new LocationKeyVisitor());
			if (!typedOnly) {
				nodes.put(key, target);
			}
			if (target instanceof Node) {
				GuidUtil.setGuid((Node) target, key);
			} else if (target instanceof IPropertyAware) {
				GuidUtil.setGuid((IPropertyAware) target, key);
			}
			propertyMap.put(LOCATION_CONTEXT_PROPERTY, context);
		}

		//typed are referenced by parent location
		int lastSeparator = key.lastIndexOf(LocationKeyVisitor.KEY_SEPARATOR);
		if (lastSeparator > 0) {
			String parentKey = key.substring(0, lastSeparator);
			String simple =  key.substring(lastSeparator);
			TypedCtx typed = typedNodes.get(key);
			if (typed == null) {
				typed = new TypedCtx();
				typedNodes.put(parentKey, typed);
			}
			List<EventTarget> targetList = typed.typedNodes.computeIfAbsent(target.getClass(), x -> new ArrayList<>());
			targetList.add(target);
			propertyMap.put(LOCATION_CONTEXT_SIMPLE_NAME, simple);
		}
	}

	/**
	 * Register a node with a UUID
	 * @param guid the uuid to use
	 * @param node the node to register
	 * @deprecated Use {@link #register(String, Node)} instead
	 */
	@Deprecated
	public void register(UUID guid, Node node) {
		nodes.put(guid.toString(), node);
	}

	/**
	 * Register a node with a GUID
	 * @param guid the uuid to use
	 * @param node the node to register
	 */
	public void register(String guid, Node node) {
		nodes.put(guid, node);
	}

	/**
	 * Get the node registered under a UUID
	 * @param guid the uuid to reference
	 * @param <T> the type of the JavaFX Component
	 * @return the node registered with the uuid
	 * @deprecated Use {@link #get(String)} instead
	 */
	@Deprecated
	public <T extends EventTarget>  T get(UUID guid) {
		return (T) nodes.get(guid.toString());
	}

	/**
	 * Get the node registered under a guid
	 * @param guid the uuid to reference
	 * @param <T> the type of the JavaFX Component
	 * @return the node registered with the uuid
	 */
	public <T extends EventTarget>  T get(String guid) {
		return (T) nodes.get(guid);
	}

	@Override
	public <T extends EventTarget> T get(ITypedComponentQuery query) throws CoreException {
		return CoreException.unwrap(() -> (T) new Step<>(new Base<>(
				typedNodes.get(query.getQueryString())
		), typed -> CoreException.wrap(() -> {
					List<EventTarget> targetList = typed.typedNodes.get(query.getType());
					EventTarget result = null;
					if (targetList != null) {
						if (targetList.size() != 1) {
							throw new CoreException("Unable to locate item. Too many candidates: " + targetList.size());
						} else {
							result = targetList.get(0);
						}
					}
					return result;
				})).execute());
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
			List<EventTarget> targetList = typed.typedNodes.get(node.getClass());
			if (targetList != null) {
				targetList.remove(node);
			}
		}
	}

	private class TypedCtx {

		private final Map<Class, List<EventTarget>> typedNodes = new HashMap<>();

	}
}
