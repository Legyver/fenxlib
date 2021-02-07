package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.control.Label;

public class LabelFactory implements NodeFactory<Label> {

	private final String text;
	
	public LabelFactory(String text) {
		this.text = text;
	}

	public Label makeLabel() {
		Label label = new Label(text);
		label.autosize();
		return label;
	}

	@Override
	public Label makeNode(LocationContext locationContext) {
		return makeLabel();
	}
}
