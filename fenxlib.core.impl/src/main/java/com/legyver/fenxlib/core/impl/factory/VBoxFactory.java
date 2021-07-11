package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.VBox;


/**
 * Factory to wrap the encapsulated factories output in a VBox
 */
public class VBoxFactory extends AbstractWrappingFactory<Node> implements NodeFactory<VBox> {

	/**
	 * Construct a VBoxFactory with the factories for child nodes whose output will be wrapped by this VBox
	 * @param nodeFactories factories to produce the elements in this VBox
	 */
	public VBoxFactory(NodeFactory... nodeFactories) {
		super(nodeFactories);
	}

	/**
	 * Make a VBox wrapping any children
	 * @param locationContext the location context determining the section of the application to register any chlidren
	 * @return the VBox
	 * @throws CoreException if there is an Exception raised by any of the wrapped child factories
	 */
	public VBox makeVBox(LocationContext locationContext) throws CoreException {
		List<Node> entries = makeChildren(locationContext);
		return new VBox(entries.toArray(new Node[entries.size()]));
	}

	@Override
	public VBox makeNode(LocationContext locationContext) throws CoreException {
		return makeVBox(locationContext);
	}
}
