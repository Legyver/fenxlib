package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.scene.control.Label;

/**
 * A factory for a label that's text property is bound to a component created by the specified factory
 */
public class BoundLabelFactory implements NodeFactory<Label> {
	private final BindableValueFactory bindableValueFactory;

	/**
	 * Construct a factory to create a Label that's text property is bound to the specified factory
	 * @param bindableValueFactory the factory
	 */
	public BoundLabelFactory(BindableValueFactory bindableValueFactory) {
		this.bindableValueFactory = bindableValueFactory;
	}

	@Override
	public Label makeNode(LocationContext locationContext) {
		Label label = new Label();
		label.textProperty().bind(bindableValueFactory.boundProperty());
		ApplicationContext.getComponentRegistry().register(locationContext, label);
		return label;
	}

}
