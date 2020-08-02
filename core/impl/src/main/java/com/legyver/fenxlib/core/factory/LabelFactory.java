package com.legyver.fenxlib.core.factory;

import javafx.scene.control.Label;
import com.legyver.fenxlib.core.locator.LocationContext;

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
