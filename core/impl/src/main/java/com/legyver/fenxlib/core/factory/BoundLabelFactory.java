package com.legyver.fenxlib.core.factory;

import com.legyver.fenxlib.core.context.ApplicationContext;
import javafx.scene.control.Label;
import com.legyver.fenxlib.core.locator.LocationContext;

public class BoundLabelFactory implements NodeFactory<Label> {
	private final BindableValueFactory bindableValueFactory;

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
