package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;

import java.util.List;

import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;


/**
 * Factory to wrap the encapsulated factories output in a VBox
 */
public class VBoxFactory extends AbstractWrappingFactory<Node> implements StyleableFactory<VBox> {

	/**
	 * Construct a VBoxFactory with the factories for child nodes whose output will be wrapped by this VBox
	 * @param nodeFactories factories to produce the elements in this VBox
	 */
	public VBoxFactory(NodeFactory... nodeFactories) {
		super(nodeFactories);
	}

	/**
	 * Construct a VBoxFactory with the factories for child nodes whose output will be wrapped by this VBox
	 * @param nodeFactories factories to produce the elements in this VBox
	 */
	public VBoxFactory(List<? extends Styleable> nodeFactories) {
		super((List) nodeFactories);
	}

	@Override
	public VBox makeNode(LocationContext locationContext) throws CoreException {
		List<Node> entries = makeChildren(locationContext);
		VBox vbox = makeVBox();
		vbox.getChildren().addAll(entries);
		return vbox;
	}

	/**
	 * Factory method to specify a new instance of a VBox.
	 * By default, returns a basic javafx {@link VBox}.  Override if you want a specific library implementation.
	 * @return a VBox
	 */
	protected VBox makeVBox() {
		return new VBox();
	}
}
