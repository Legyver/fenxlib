package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class AbstractWrappingFactory<T extends Node> {

	protected final NodeFactory<T>[] nodeFactories;

	public AbstractWrappingFactory(NodeFactory... nodeFactories) {
		this.nodeFactories = nodeFactories;
	}

	protected void addChildren(ObservableList<T> childs, LocationContext locationContext) throws CoreException {
		if (nodeFactories != null) {
			makeChildStream(locationContext)
					.forEach((T n) -> childs.add(n));
		}
	}

	private Stream<T> makeChildStream(LocationContext locationContext) throws CoreException {
		return (Stream<T>) unwrap(() -> Stream.of(nodeFactories)
				.map((NodeFactory f) -> wrap(() -> f.makeNode(locationContext))));
	}

	protected List<T> makeChildren(LocationContext locationContext) throws CoreException {
		List<T> childList = Collections.EMPTY_LIST;
		if (nodeFactories != null) {
			childList = makeChildStream(locationContext)
					.collect(Collectors.toList());
		}
		return childList;
	}

}
