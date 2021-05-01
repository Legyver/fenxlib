package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.StyleableFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.ObservableList;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;
import javafx.css.Styleable;

/**
 * Abstract factory for any factory that has child components created by nested factories
 * @param <T> the type of the widget being created
 */
public class AbstractWrappingFactory<T extends Styleable> {

	/**
	 * Nested factories for creating embedded widgets
	 */
	protected final StyleableFactory<? extends T>[] styleableFactories;

	/**
	 * Construct a wrapping factory for the specified child factories
	 * @param styleableFactories child factories
	 */
	public AbstractWrappingFactory(StyleableFactory<? extends T>... styleableFactories) {
		this.styleableFactories = styleableFactories;
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
