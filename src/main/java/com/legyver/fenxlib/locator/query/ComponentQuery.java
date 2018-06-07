package com.legyver.fenxlib.locator.query;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.scene.Node;

import static com.legyver.fenxlib.locator.visitor.LocationKeyVisitor.KEY_SEPARATOR;

public class ComponentQuery<T extends Node> {
	private final String queryString;
	private final QueryableComponentRegistry registry;

	public ComponentQuery(String queryString, QueryableComponentRegistry registry) {
		this.queryString = queryString;
		this.registry = registry;
	}

	public String getQueryString() {
		return queryString;
	}

	public Optional<T> execute() {
		return Optional.ofNullable((T) registry.get(queryString));
	}

	protected static abstract class AbstractQueryBuilder {
		protected String name;
		abstract protected ComponentQuery build(Deque<String> stack);
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

		@Override
		protected ComponentQuery build(Deque<String> stack) {
			stack.push(name);
			stack.stream().collect(Collectors.joining(KEY_SEPARATOR));

			return new ComponentQuery(stack.stream().collect(Collectors.joining(KEY_SEPARATOR)), registry);
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

	}

}
