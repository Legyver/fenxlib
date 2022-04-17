package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.collections.ObservableList;
import javafx.css.Styleable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract factory for any factory that has child components created by nested factories
 * @param <T> the type of the widget being created
 */
public class AbstractWrappingFactory<T extends Styleable> {
	/**
	 * Constructor parameter to specify child to be wrapped by this control produced by this factory
	 */
	public static final String CHILDREN = "children";

	/**
	 * Nested factories for creating embedded widgets
	 */
	protected final StyleableFactory<? extends T>[] styleableFactories;
	/**
	 * Children to be wrapped by this control produced by this factory
	 */
	protected final List<? extends T> children;

	/**
	 * Construct a wrapping factory for the specified child factories
	 * @param styleableFactories child factories
	 * @deprecated Use {@link #AbstractWrappingFactory(List)} where the children are already constructed
	 */
	@Deprecated
	public AbstractWrappingFactory(StyleableFactory<? extends T>... styleableFactories) {
		this.styleableFactories = styleableFactories;
		this.children = null;
	}

	/**
	 * Construct a wrapping factory for the specified children
	 * @param children children to wrap
	 */
	public AbstractWrappingFactory(List<? extends T> children) {
		this.children = children;
		this.styleableFactories = null;
	}

	/**
	 * Add children to the specified list
	 * @param childs the list to add the children to
	 * @param locationContext the location context when constructing the children
	 * @throws CoreException if there is an error during child component creation
	 */
	protected void addChildren(ObservableList<T> childs, LocationContext locationContext) throws CoreException {
		if (styleableFactories != null) {
			makeChildStream(locationContext)
					.forEach((T n) -> childs.add(n));
		}
		if (children != null) {
			childs.addAll(children);
		}
	}

	private Stream<T> makeChildStream(LocationContext locationContext) throws CoreException {
		return (Stream<T>) CoreException.unwrap(() -> Stream.of(styleableFactories)
				.map((StyleableFactory f) -> CoreException.wrap(() -> f.makeNode(locationContext))));
	}

	/**
	 * Create a list of child compoenents
	 * @param locationContext the location context when constructing the children
	 * @return the list of children
	 * @throws CoreException if there is an error during child component creation
	 */
	protected List<T> makeChildren(LocationContext locationContext) throws CoreException {
		List<T> childList = Collections.EMPTY_LIST;
		if (styleableFactories != null) {
			childList = makeChildStream(locationContext)
					.collect(Collectors.toList());
		}
		return childList;
	}

}
