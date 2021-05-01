package com.legyver.fenxlib.core.api.locator.query;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.visitor.LocationKeyVisitor;
import com.legyver.fenxlib.core.api.context.BaseApplicationContext;
import com.legyver.fenxlib.core.api.locator.LocationContext;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.Node;

/**
 * Query for a component
 * @param <T> the type of the component
 */
public abstract class ComponentQuery<T extends Node> {

	private final String queryString;
	final QueryableComponentRegistry registry = BaseApplicationContext.getComponentRegistry();

	ComponentQuery(String queryString) {
		this.queryString = queryString;
	}

	/**
	 * Get the query string
	 * @return the query string
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * Execute the query
	 * @return the component
	 */
	public abstract Optional<T> execute();

	private static class NamedComponentQuery<T extends Node> extends ComponentQuery<T> implements INamedComponentQuery<T> {

		private NamedComponentQuery(String queryString) {
			super(queryString);
		}

		@Override
		public Optional<T> execute() {
			return Optional.ofNullable((T) registry.get(this));
		}
	}

	private static class TypedComponentQuery<T extends Node> extends ComponentQuery<T> implements ITypedComponentQuery<T> {

		private final Class type;

		private TypedComponentQuery(String queryString, Class type) {
			super(queryString);
			this.type = type;
		}

		@Override
		public Class getType() {
			return type;
		}

		@Override
		public Optional<T> execute() {
			return Optional.ofNullable((T) registry.get(this));
		}
	}

	static abstract class AbstractQueryBuilder {

		String name;

		abstract ComponentQuery build(Deque<String> stack);

		abstract ComponentQuery build(Deque<String> stack, Class type);
	}

	/**
	 * Fluent builder to create a query
	 */
	public static class QueryBuilder extends AbstractQueryBuilder {

		/**
		 * The region to query in
		 * @param name the name of the region
		 * @return the query builder
		 */
		public IRegionDiscriminator inRegion(String name) {
			this.name = name;
			return new RegionIQueryBuilder(this);
		}

		/**
		 * The location to query in
		 * @param locationContext the location
		 * @return the query builder
		 */
		public IRegionDiscriminator fromLocation(LocationContext locationContext) {
			String key = locationContext.accept(new LocationKeyVisitor());
			this.name = key;
			return new RegionIQueryBuilder(this);
		}

		@Override
		ComponentQuery build(Deque<String> stack) {
			if (name != null) {
				stack.push(name);
			}
			return new NamedComponentQuery(stack.stream().collect(Collectors.joining(LocationKeyVisitor.KEY_SEPARATOR)));
		}

		@Override
		ComponentQuery build(Deque<String> stack, Class type) {
			stack.push(name);
			stack.stream().collect(Collectors.joining(LocationKeyVisitor.KEY_SEPARATOR));

			return new TypedComponentQuery(stack.stream().collect(Collectors.joining(LocationKeyVisitor.KEY_SEPARATOR)), type);
		}
	}

	abstract static class ChildIQueryBuilder<T extends AbstractQueryBuilder, U extends ChildIQueryBuilder> extends AbstractQueryBuilder implements IQueryDiscriminator {

		final T parent;

		ChildIQueryBuilder(T parent) {
			this.parent = parent;
		}

		/**
		 * Query for the only component at the specified location
		 * @return the query
		 */
		@Override
		public ComponentQuery only() {
			return build(new ArrayDeque<>());
		}

		/**
		 * Query for the component named a specific value at the specified location
		 * @param name the name of the component
		 * @return the query
		 */
		@Override
		public ComponentQuery named(String name) {
			Deque<String> stack = new ArrayDeque<>();//since we're building from the botton, using a stack
			stack.push(name);
			return build(stack);
		}

		/**
		 * Query for the component of a specific type at the specified location
		 * @param type the type of the component
		 * @return the Query
		 */
		@Override
		public ComponentQuery type(Class type) {
			return build(new ArrayDeque<>(), type);
		}
	}

	/**
	 * Query
	 * @param <T>
	 */
	static class RegionIQueryBuilder<T extends AbstractQueryBuilder> extends ChildIQueryBuilder<T, RegionIQueryBuilder> implements IRegionDiscriminator {

		RegionIQueryBuilder(T parent) {
			super(parent);
		}

		@Override
		public RegionIQueryBuilder inSubRegion(String name) {
			this.name = name;
			return new RegionIQueryBuilder(this);
		}

		@Override
		protected ComponentQuery build(Deque<String> stack) {
			if (name != null) {
				stack.push(name);
			}
			return parent.build(stack);
		}

		@Override
		protected ComponentQuery build(Deque<String> stack, Class type) {
			if (name != null) {
				stack.push(name);
			}
			return parent.build(stack, type);
		}

	}

}
