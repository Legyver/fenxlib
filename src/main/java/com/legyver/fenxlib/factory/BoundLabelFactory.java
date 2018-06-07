package com.legyver.fenxlib.factory;

import javafx.scene.control.Label;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.util.GuiUtil;

public class BoundLabelFactory implements NodeFactory<Label> {
	private final BindableValueFactory bindableValueFactory;

	public BoundLabelFactory(BindableValueFactory bindableValueFactory) {
		this.bindableValueFactory = bindableValueFactory;
	}

	@Override
	public Label makeNode(LocationContext locationContext) {
		Label label = new Label();
		label.textProperty().bind(bindableValueFactory.boundProperty());
		GuiUtil.getComponentRegistry().register(locationContext, label);
		return label;
	}

}
