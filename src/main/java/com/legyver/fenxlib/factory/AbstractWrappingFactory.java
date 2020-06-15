package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.ObservableList;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;
import javafx.css.Styleable;

public class AbstractWrappingFactory<T extends Styleable> {

	protected final StyleableFactory<? extends T>[] styleableFactories;

	public AbstractWrappingFactory(StyleableFactory<? extends T>... styleableFactories) {
		this.styleableFactories = styleableFactories;
	}

	protected void addChildren(ObservableList<T> childs, LocationContext locationContext) throws CoreException {
		if (styleableFactories != null) {
			makeChildStream(locationContext)
					.forEach((T n) -> childs.add(n));
		}
	}

	private Stream<T> makeChildStream(LocationContext locationContext) throws CoreException {
		return (Stream<T>) unwrap(() -> Stream.of(styleableFactories)
				.map((StyleableFactory f) -> wrap(() -> f.makeNode(locationContext))));
	}

	protected List<T> makeChildren(LocationContext locationContext) throws CoreException {
		List<T> childList = Collections.EMPTY_LIST;
		if (styleableFactories != null) {
			childList = makeChildStream(locationContext)
					.collect(Collectors.toList());
		}
		return childList;
	}

}
