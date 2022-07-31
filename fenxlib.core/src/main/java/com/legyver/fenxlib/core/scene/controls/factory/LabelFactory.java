package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.LabelOptions;
import javafx.scene.control.Label;

/**
 * Factory to create a label
 */
public class LabelFactory implements StyleableFactory<Label, LabelOptions> {

	@Override
	public Label makeNode(LocationContext locationContext, LabelOptions options) {
		Label label = makeLabel();
		label.autosize();
		if (options.getTextProperty() != null) {
			label.textProperty().bind(options.getTextProperty());
			if (options.getText() != null) {
				options.getTextProperty().set(options.getText());
			}
		} else if (options.getText() != null) {
			label.setText(options.getText());
		}

		ApplicationContext.getComponentRegistry().register(locationContext.decorateWith(options.getName()), label);
		return label;
	}

	@Override
	public LabelOptions newOptions() {
		return new LabelOptions();
	}

	/**
	 * Make the label
	 * @return the label
	 */
	protected Label makeLabel() {
		Label label = new Label();
		return label;
	}

}
