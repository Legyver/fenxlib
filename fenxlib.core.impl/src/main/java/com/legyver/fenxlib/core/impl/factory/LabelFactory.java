package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.control.Label;

/**
 * Factory to create a label
 */
public class LabelFactory implements NodeFactory<Label> {

	private final String text;

	/**
	 * Construct a factory to create a label with given text
	 * @param text the text for the label
	 */
	public LabelFactory(String text) {
		this.text = text;
	}

	/**
	 * Make the label
	 * @return the label
	 */
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
