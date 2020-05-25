package com.legyver.fenxlib.locator.query;

import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.visitor.LocationKeyVisitor;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.scene.Node;

import static com.legyver.fenxlib.locator.visitor.LocationKeyVisitor.KEY_SEPARATOR;

public abstract class ComponentQuery<T extends Node> {

	private final String queryString;
	protected final QueryableComponentRegistry registry;

	public ComponentQuery(String queryString, QueryableComponentRegistry registry) {
		this.queryString = queryString;
		this.registry = registry;
	}

	public String getQueryString() {
		return queryString;
	}

	public abstract Optional<T> execute();

	private static class NamedComponentQuery<T extends Node> extends ComponentQuery<T> implements INamedComponentQuery<T> {

		private NamedComponentQuery(String queryString, QueryableComponentRegistry registry) {
			super(queryString, registry);
		}

		@Override
		public Optional<T> execute() {
			return Optional.ofNullable((T) registry.get(this));
		}
	}

	private static class TypedComponentQuery<T extends Node> extends ComponentQuery<T> implements ITypedComponentQuery<T> {

		private final Class type;

		private TypedComponentQuery(String queryString, Class type, QueryableComponentRegistry registry) {
			super(queryString, registry);
			this.type = type;
		}

		public Class getType() {
			return type;
		}

		@Override
		public Optional<T> execute() {
			return Optional.ofNullable((T) registry.get(this));
		}
	}

	protected static abstract class AbstractQueryBuilder {

		protected String name;

		abstract protected ComponentQuery build(Deque<String> stack);

		abstract protected ComponentQuery build(Deque<String> stack, Class type);
	}

	public static class QueryBuilder extends AbstractQueryBuilder {

		private final QueryableComponentRegistry registry;

		public QueryBuilder(QueryableComponentRegistry registry) {
			this.registry = registry;
		}

		public RegionQueryBuilder inRegion(String name) {
			this.name = name;
			return new RegionQueryBuilder(this);
		}

		public RegionQueryBuilder fromLocation(LocationContext locationContext) {
			String key = locationContext.accept(new LocationKeyVisitor());
			this.name = key;
			return new RegionQueryBuilder(this);
		}

		@Override
		protected ComponentQuery build(Deque<String> stack) {
			if (name != null) {
				stack.push(name);
			}
			return new NamedComponentQuery(stack.stream().collect(Collectors.joining(KEY_SEPARATOR)), registry);
		}

		@Override
		protected ComponentQuery build(Deque<String> stack, Class type) {
			stack.push(name);
			stack.stream().collect(Collectors.joining(KEY_SEPARATOR));

			return new TypedComponentQuery(stack.stream().collect(Collectors.joining(KEY_SEPARATOR)), type, registry);
		}
	}

	protected abstract static class ChildQueryBuilder<T extends AbstractQueryBuilder, U extends ChildQueryBuilder> extends AbstractQueryBuilder {

		protected final T parent;

		public ChildQueryBuilder(T parent) {
			this.parent = parent;
		}

		public ComponentQuery only() {
			return build(new ArrayDeque<>());
		}

		public ComponentQuery named(String name) {
			Deque<String> stack = new ArrayDeque<>();//since we're builing from the botton, using a stack
			stack.push(name);
			return build(stack);
		}

		public ComponentQuery type(Class type) {
			return build(new ArrayDeque<>(), type);
		}
	}

	public static class RegionQueryBuilder<T extends AbstractQueryBuilder> extends ChildQueryBuilder<T, RegionQueryBuilder> {

		public RegionQueryBuilder(T parent) {
			super(parent);
		}

		public RegionQueryBuilder inSubRegion(String name) {
			this.name = name;
			return new RegionQueryBuilder(this);
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
